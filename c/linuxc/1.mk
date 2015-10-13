#make file for 1.c
#use:		make -f 1.mk
EXE = debug/main
OBJ = 1.o
SRC = 1.c
CC = gcc
EXE: $(OBJ)
	$(CC) -o $(EXE) $^
.PHONY:clean
clean:
	-rm -vfr $(OBJ) $(EXE)
