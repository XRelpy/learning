#!/bin/bash

curDir=$(pwd)

source /media/mark/SSD_Disk/Hades_MR3/build/envsetup.sh

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
