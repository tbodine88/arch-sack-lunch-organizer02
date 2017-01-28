#! /bin/sh
# A simple script to send email from Thomas Bodine's email account on
# Cactus
if [ -e message ] ; then
  cat message.txt | ssh tbodine@outserv.cactus.org mail -s archSackLunchMessage tom@tommythegeek.com
  rm message
else
  pwd	
  echo no message
fi
