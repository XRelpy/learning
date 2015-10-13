#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <getopt.h>


#define MINIDATA	32
#define MIDIUDATA	512
#define MAXDATA		10240 

const char * program_name;

void debug(char *info)
{
#ifdef DEBUG	
	printf("[DEBUG]  %s\n", info);
#endif	
}


void print_usage(FILE *stream, int exit_code)
{
	debug("print_usage is running");
	fprintf(stream,"Usage %s options [ inputfile ... ]\n", program_name);
	fprintf(stream,
		"	-h --help Display this usage information.\n"
		"	-o --output filename Write output to file.\n"
		"	-v --verbose Print verbose messages.\n");
	exit(exit_code);
}


int main(int argc, char *argv[])
{
	debug("setup 1");
	int next_option;
	const char * const short_options = "hov:";
	const struct option long_options[] = {
		{"help", 0 , NULL, 'h'},
		{"output", 1, NULL, 'o'},
		{"verbose", 0, NULL, 'v'},
		{NULL, 0, NULL , 0}
	};
	debug("setup 2");
	const char * output_filename = NULL;
	int verbose = 0;
	program_name = (char *)malloc(MINIDATA);
	program_name = argv[0];
	do{
		next_option = getopt_long(argc, argv, short_options, long_options, NULL);
	char *info;
	info = (char *)malloc(MINIDATA);
	sprintf(info,"next_option:%d", next_option);
	debug(info);
	debug("setup 3");
		switch(next_option)
		{
			debug("setup 4");
			case 'h' : 
				print_usage(stdout, 0);
			case 'o':
				output_filename = optarg;
				printf("option : o \n");
				break;
			case '?':
				print_usage(stderr, 1);
		}
	} while(next_option != -1);
	
	
	return 0;
}
