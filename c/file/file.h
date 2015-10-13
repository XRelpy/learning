#ifndef __FILE_H
#define __FILE_H
#include <stdio.h>

extern unsigned int createFs(char *fileName);
extern unsigned int mopen(char *fileName);
extern void mread(unsigned int fd, char *data);
extern void mwrite(unsigned int fd, char *data);
extern void mclose(unsigned int fd);
#endif
