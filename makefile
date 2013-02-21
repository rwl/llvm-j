# Makefile for LLVM-J

LLVM_VERSION=3.1
JNAERATOR_VERSION=0.11-shaded

# Path to LLVM such that headers for the C bindings exist
# in $LLVM_HOME/include/llvm-c/
LLVM_HOME=/usr/lib/llvm-$(LLVM_VERSION)

JAVA=java

.PHONY: help clean llvm

.DEFAULT_GOAL := help
	
help:
	@echo "Use \`make <target> [LLVM_HOME=<path>]' where <path> is the"
	@echo "location of LLVM such that headers for the C-bindings"
	@echo "exist in \$$LLVM_HOME/include/llvm-c and <target> is one of"
	@echo "    help     to display this help message"
	@echo "    clean    to remove existing bindings"
	@echo "    llvm     to generate Java bindings to LLVM using JNAerator"

clean:
	-rm src/main/java/llvm/binding/LLVMLibrary.java

llvm: clean src/main/java/llvm/binding/LLVMLibrary.java

src/main/java/llvm/binding/LLVMLibrary.java: jnaerator.jar config.jnaerator
	$(JAVA) -jar $<
	sed -i 's/@Library("LLVM")/@Library("LLVM-$(LLVM_VERSION)")/' $@

jnaerator.jar:
	wget http://jnaerator.googlecode.com/files/jnaerator-$(JNAERATOR_VERSION).jar -O $@
