#!/bin/bash

curDir=$(pwd)

#source $(code)/build/envsetup.sh
#here need sue system mm env

function debug() {
    echo CurrentDir:${curDir}

}

debug

for dir in $(ls) 
do
    if [ -d $dir ]
    then
        echo $dir
        cd $dir
        mm
        cd $curDir
    fi
done
