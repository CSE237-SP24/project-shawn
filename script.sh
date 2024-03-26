#!/bin/bash

# 进入包含 Java 源文件的目录
cd src

# 编译 bankapp 包下的所有 Java 文件
javac bankapp/*.java

# 运行 Menu 类，假设它包含 main 方法
java bankapp.Menu


