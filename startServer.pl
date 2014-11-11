#!/usr/bin/perl
use Win32::OLE ; 
use Win32::GuiTest;

system("start cmd.exe");
sleep 3;

Win32::GuiTest::SendKeys("mongod --dbpath C:\\mongodb\\bin\\data\\db  {ENTER}");

system("start cmd.exe");
sleep 3;
Win32::GuiTest::SendKeys("mongo  {ENTER}");

sleep 3;