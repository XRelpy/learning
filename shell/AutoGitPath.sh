#!/bin/bash

#
#此脚本主要功能是将: 某一作者的提交全部拉出来，放置在workDir中
#
#config 

workDir=M_Patch
git_author=mark_xiao
commit_info=git_commit
Git=git

function GetCommit() {
    if [ ! -d "$workDir" ]; then
        mkdir $workDir
    else 
        rm -Rf $workDir
        mkdir $workDir
    fi

    $Git  log --author=$git_author --oneline >> $workDir/$commit_info
    echo "*****************************"
    $Git  log --author=$git_author >> $workDir/commit_info.dat
}

function CreatePatch() {
    if [ ! -f "$workDir/$commit_info" ]; then
        echo "Error: Not Exist $workDir/$commit_info" 
    else 
        i=0
        while read line
        do 
            let i+=1
            echo "line $i:"  $line
            commit=${line:0:7}
            echo "$commit  ============================"
            $Git format-patch -1 $commit -o $workDir
        done < $workDir/$commit_info
    fi
}

function main() {
    GetCommit
    CreatePatch
}


main
