all: Driver.java
	@javac Driver.java
	@java Driver
	@rm *.class

clean:
	@rm *.class
