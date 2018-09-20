CC = javac
SRC = test.java
#LIBS = -lGL -lGLU -lglut
EXEC = test.class

all:
	$(CC) $(SRC) 

clean:
	rm -rf $(EXEC) *~

run:
	java test

linked:
	javac Solution.java



