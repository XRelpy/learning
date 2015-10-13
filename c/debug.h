#ifndef __DEBUG_H
#define __DEBUG_H

#define MINIDATA 128
#define true 1
#define false 0

/*
	#include "debug.h"
		open debug: debug(true)
		close debug: deubg(false)
	
	print string data:
		debugc(string);
	print int data;
		debug(string , int);
*/

void extern debug(int deb);
void extern debugc(char *p);
void extern debugi(char *i, int p);
#endif
