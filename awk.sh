#!/usr/bin/awk -f

BEGIN{
	FS=":"
	print "----------------Start-----------------"
}
function passwdText() {
#	print $1 " " $3	
#	print $0
	username = "root"	
	if ((ARGV[1])=="passwd")	{
	#	printf "username: %-24s uid:%s\n",$1,$3
	#	printf "%s\n", username
		if ($1 ~ /root/){
			print $0
		}	
	}else	{
	}
}
	
function findCfileFun(arg1)
{
#	if (arg1 ~ /void.*.(.*.)/){
	if(arg1 ~ //)
		printf "%s\n",arg1
	}	
}

{
#	passwdText()
#	findCfileFun($0)

}

END{
	print "-----------------End------------------"
}

