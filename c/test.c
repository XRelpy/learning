#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "debug.h"

//extern void debug();
//extern void debugc(char *p);
//extern void debugi(char *i, int p);

int main(void)
{
	debug(true);
	debugc("This is a debug test");
	debugi("Test",233333);
	return 0;
}
