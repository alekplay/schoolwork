import numpy as np
import sys
import matplotlib as mp
import matplotlib.pyplot as plt
import matplotlib.animation as animation
import obj2clist as obj

####################################################
# modify the following 5 functions
# all functions assume homogeneous coordinates in 3D
####################################################
def project(d):
    """
    returns the projection matrix corresponding to having the viewpoint at (0,0,d)
    and the viewing plane at z=0 (the xy plane).
    """
    return(np.array([[1,0,0,0],[0,1,0,0],[0,0,0,0],[0,0,-1/d,1]]))

def moveTo(start, end):
    """
    returns the matrix corresponding to moving an obj from position 'start' to position 'end.'
    positions are given in 3D homogeneous coordinates.
    """
    # your code here


def rotate(x,y,z,loc):
    """
    returns the matrix corresponding to first rotating a value 'x' around the x-axis,
    then rotating 'y' around the y-axis, and then 'z' around the z-axis.   All angles
    are in radians. The center of rotation is at point given by 'loc' (3D homogeneous coord).
    """
    # your code here

def ballTransform(i,loc):
    """
    returns the appropriate transformation matrix for the ball.  The center of the ball
    before transformation is given by 'loc'.  The appropriate transformation depends on the
    timestep which is given by 'i'.
    """
    A = np.multiply(project(100), loc)
    return A

def houseTransform(i,loc):
    """
    returns the appropriate transformation matrix for the house.  The center of the house
    before transformation is given by 'loc'.  The appropriate transformation depends on the
    timestep which is given by 'i'.
    """
    A = np.multiply(project(100), loc)
    return A

#######################################
# No need to change any code below here
#######################################
def scale(f):
    """
    returns a matrix that scales a point by a factor f
    """
    return(np.array([[f,0.,0,0],[0,f,0,0],[0,0,f,0],[0,0,0,1]]))

# This function implements the animation.  It will be called automatically if you
# run this entire file in the python interpreter.  Or you call call runShow() directly from the
# interpreter prompt if you wish.
def runShow():

    # read house data
    # house is 10*houseScale feet high
    with open('basicHouse.obj','r') as fp:
        house = obj.obj2flist(fp)
    house = obj.homogenize(house)
    houseScale = 3.0
    S = scale(houseScale)
    d = np.array([-5., 4., 3., 1]) - obj.objCenter(house) 
    M = np.array([[1.,0,0,d[0]],[0,1,0,d[1]],[0,0,1,d[2]],[0,0,0,1]])
    house = [S.dot(M).dot(f) for f in house]

    # read ball data
    # ball has radius equal to ballScale feet
    with open('snub_icosidodecahedron.wrl','r') as fp:
        ball = obj.wrl2flist(fp)
    ball = obj.homogenize(ball)
    ballScale = 2.0
    S = scale(ballScale)
    d = np.array([10.0, -0.5, 0., 1]) - obj.objCenter(ball)
    M = np.array([[1.,0,0,d[0]],[0,1,0,d[1]],[0,0,1,d[2]],[0,0,0,1]])
    ball = [S.dot(M).dot(f) for f in ball]

    # set up drawing region
    fig = plt.figure()
    ax = plt.axes(xlim=(-50,50),ylim=(-50,50))
    plt.plot(-40,-40,'')
    plt.plot(40,40,'')
    plt.axis('equal')

    # create drawables
    ballLines = []
    for b in ball:
        ballLines += ax.plot([],[],'b')
    houseLines = []
    for h in house:
        houseLines += ax.plot([],[],'r')

    # this is the drawing routine that will be called on each timestep
    def animate(i):
        M = ballTransform(i,obj.objCenter(ball))
        for b,l in zip(ballLines, ball):
            n = M.dot(l)
            b.set_data(n[0]/n[3],n[1]/n[3])
        M = houseTransform(i,obj.objCenter(house))
        for b,l in zip(houseLines, house):
            n = M.dot(l)
            b.set_data(n[0]/n[3],n[1]/n[3])
        fig.canvas.draw()
        return houseLines,ballLines
    
    # instantiate the animator.
    # we are animating at max rate of 25Hz
    # about the slowest that gives a sense of continuous motion
    # but this will slow down if the scene takes too long to draw
    anim = animation.FuncAnimation(fig, animate, 
                                    frames=150, interval=1000/25, repeat=False, blit=False)
    plt.show()
    
if __name__ == "__main__":
    runShow()


    
