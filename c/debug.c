#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "debug.h"

static int initdeb = 0;

void debug(int deb)
{
	initdeb = deb;
}

void debugc(char *p)
{
	if(initdeb)
		printf("[Debug]  %s\n", p);
}

void debugi(char *i , int p)
{
	if(initdeb)
		printf("[Debug] %s -- %d\n", i, p);
}
