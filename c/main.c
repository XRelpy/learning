#include <stdio.h>
#include <stdlib.h>
#include "types.h"

int main(int argc, char * argv[])
{
	printf("Size u8:%x\n", (u32)sizeof(u8));
	printf("Size u16:%x\n", (u32)sizeof(u16));	
	printf("Size u32:%x\n", (u32)sizeof(u32));
	
	printf("Size u64:%x\n", (u32)sizeof(u64));
	return 0;
}
