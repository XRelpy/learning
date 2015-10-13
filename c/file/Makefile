obj:=main
src:=file.o main.o
outDir:=./out

$(obj):$(src)
	gcc -o $(outDir)/$(obj)  $^
main.o:main.c 
	gcc -c $^
file.o:file.c file.h
	gcc -c $^

.PHYON:	clean
clean:
	@rm *.o

