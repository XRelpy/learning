#include <stdio.h>

typedef unsigned char u8;
typedef unsigned short u16;
typedef signed short i16;
typedef unsigned int u32;

typedef struct {
	u32 h;
	u32 l;
} u64;

static inline u64 U64(u32 low, u32 high)
{
	u64 self;
	self.l = low;
	self.h = high;
	return self;
}

int main(int argc, char *argv[])
{
	u64 a;
	a.h = 0x85;
	a.l = 0x87;
//	printf("h:%x l:%x\n", a.h, a.l);
	u64 b;
	b = a;
	printf("%x%08x\n", b.h, b.l);	

	return 0;
}
