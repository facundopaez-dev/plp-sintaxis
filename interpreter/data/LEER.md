### Casos de prueba negativos
#### Archivos failTest1.in y failTest2.in
Estos casos de prueba son negativos (es decir, el intérprete reconoce un error de sintaxis) porque la palabra "pits" en la sentencia "put pits in [3,4]" del archivo failTest1.in y la palabra "heros" en la sentencia "put heros in [3,0]" del archivo failTest2.in, no pertenecen a la sintaxis con la que fue programado el intérprete.

#### Archivo failTest3.in
El intérprete reconoce como error de sintaxis aquellas sentencias put pit y put rem en las que una o más condiciones tienen a las variables i y j en el mismo término.

Este caso de prueba es negativo porque en la condición "i * j > 2" las variables i y j están en el mismo término.

#### Archivo failTest4.in
Este caso de prueba es negativo porque en la condición "i + j == 1" las variables i y j están en el mismo término.

#### Archivo failTest5.in
Este caso de prueba es negativo porque en la condición "j * 2 + 5 + i > 1" las rabiales i y j están en el mismo término.

#### Archivo failTest6.in
Este caso de prueba es negativo porque en la condición "i + 1 + j >= 1" las variables i y j están en el mismo término.

### Casos de prueba positivos
#### Archivo test1.in
Este caso de prueba es positivo porque todas las sentencias están formadas por palabras que cumplen con la sintaxis con la que fue programado el intérprete.

#### Archivos test2.in, test3.in, test4.in, test5.in y test6.in
Estos casos de prueba son positivos porque, además de que todas las sentencias están formadas por palabras que cumplen con la sintaxis del intérprete, todas las condiciones de la lista de condiciones de las sentencias put pit y put rem cumplen con el requisito de que las variables i y j no estén en el mismo término.