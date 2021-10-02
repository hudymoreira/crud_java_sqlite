# crud_java_sqlite

para executar esse programa voce precisa do drive sqlite.
nesse exemplo foi utilizado o sqlite-jdbc-3.36.0.3.jar
voce pode adquirir o mesmo em: https://github.com/xerial/sqlite-jdbc/releases

para compliar, com o JKD instalado no seu sistema opracional, abra o terminal na pasta onde se encontram os arquivos e digite:

javac Cadastro.java

para executar o programa execute:

java -classpath ".:sqlite-jdbc-3.36.0.3.jar" Cadastro

###############################################3

para compilar a segunda parte do cadastro que usa classes distintas para cada coisa usar:

javac cadastro/Cadastro.java

para executar o programa execute:

java -cp ".:sqlite-jdbc-3.36.0.3.jar" cadastro/Cadastro
