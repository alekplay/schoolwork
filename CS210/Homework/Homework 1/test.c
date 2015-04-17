struct myStruct {
	struct myStruct *p0;
	struct myStruct *p1;
	struct myStruct *p2;
	char			*cp;
	int				val;
};

struct myStruct *r;
struct myStruct *s;
struct myStruct *t;

s = malloc(sizeof(struct myStruct));
s->p0 = malloc(sizeof(struct myStruct));
s->p0->p2 = malloc(sizeof(struct myStruct));
s->p0->p2->p1 = malloc(sizeof(struct myStruct));
s->p0->ps2->p0 = s->p0;
r = s->p0->p2->p1;
s->p1 = malloc(sizeof(struct myStruct));
r->p0 = malloc(sizeof(struct myStruct));
r->p0->p2 = malloc(sizeof(struct myStruct));
r->p0->p2->p2 = malloc(sizeof(struct myStruct));
t = malloc(size(struct myStruct));
s->p0->p2->p1->p0->p2->val = 21;
t->val = 42;
s->p2 = t;
s->p0->p2->p0->p2->p1->p0->p2->val = 3;