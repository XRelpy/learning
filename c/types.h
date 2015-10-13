#ifndef __TYPES_H
#define __TYPES_H

typedef unsigned char u8;
typedef unsigned short int u16;
typedef unsigned int u32;

typedef struct {
	u32 h;
	u32 l;
} u64;

static inline u64 U64(u32 h, u32 l)
{
	u64 self;
	self.h = h;
	self.l = l;
	return self;
};

#endif
