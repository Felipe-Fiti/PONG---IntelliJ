# PONG---IntelliJ
A3 de Computação Gráfica e RA
COMPUTAÇÃO GRÁFICA E REALIDADE VIRTUAL
CRIAR UM JOGO DE PONG 
PROF. FERNANDO KAKUGAWA

Integrantes do grupo: Felipe Bianco Fitipaldi - 125111347112

João Otávio de Souza - 125111350031

João Vitor Marques de Almeida -  125111360331

Luigi Marchetti Benedito - 125111356622

Instruções para Entrega do Trabalho: 
- Este trabalho deverá ser desenvolvido em grupos de, no máximo, 5 alunos; 
- Forma de entrega: arquivo compactado (ZIP), contendo: 
 o código fonte; 
 a aplicação executável; 
 descrição de como executar e jogar; 
- Prazos de entrega e apresentação; 
 Data de Entrega: 03/12/2023 às 23:59; 
 Durante as aulas dos dias 04 e 05/12/2023 cada grupo deverá apresentar o jogo 
desenvolvido, mostrando as funcionalidades e como que foram 
implementados. 
Enunciado: 
O jogo deve começar com uma tela de apresentação em fullscreen. Nesta tela mostre o 
propósito e as regras do jogo. 
Na segunda tela, o jogo deverá ter uma bola e um bastão na janela de visualização 
conforme Figura 1 e 2. 
1. Regras do Jogo 
O bastão deve estar posicionado sempre na base da janela de visualização e ser 
deslocado no eixo x, nunca ultrapassando os limites da janela. O deslocamento do bastão 
pode ser feito pelo mouse ou pelo teclado (incluir nas regras); 
A bola deve realizar uma animação contínua de translação e mudar de direção toda vez 
que quicar em alguma extremidade da janela de visualização (incluindo o bastão). 
Figura 1 Figura 2 
Desenvolva uma estratégia para que a inclinação não seja sempre a mesma em todas as 
partes do bastão – causando sempre um movimento repetido; 
O deslocamento da bola deve ter uma velocidade aceitável para a primeira fase. A 
estratégia de deslocamento deve ser pensada pelo grupo; 
O jogo deve possuir 2 fases. O usuário deve concluir a primeira fase para avançar para a 
segunda fase. Para definir o fim da primeira fase, utilize uma pontuação fixa. 
1ª Fase 
 O jogo começa com cinco vidas e placar 0 (Zero). Cada vez que o usuário não 
conseguir rebater a bola, uma vida será perdida. 
 Cada vida deve ser representada por um objeto 2D ou 3D. O placar deve ser 
representado por números; 
 A cada rebatida na bola a pontuação será incrementada até atingir 200 pontos; 
 Se o usuário atingir 200 pontos a 2ª fase será iniciada; 
 O jogo deve ter teclas para configurar Start/Pause/Stop do jogo. 
2ª Fase 
 O deslocamento da bola deve ter sua velocidade aumentada, incluindo um grau 
de dificuldade a fase; 
 Um novo objeto deverá ser acrescentado ao centro da cena, por exemplo, um 
losango (o grupo deve escolher o objeto que achar melhor); 
 A bola então também deverá mudar a direção ao quicar no novo objeto. Ela não 
poderá passar pelo objeto em momento algum; 
 Em relação à pontuação, as regras da fase 1 continuam sendo seguidas; 
 O fim do jogo se dará com a perda de todas as vidas ou pelo acionamento da 
tecla que representa o STOP. 
O jogo deve iniciar em fullscreen e o score e vidas devem estar posicionados em um dos 
cantos da janela de visualização. 
2. Critérios de Avaliação
O trabalho deverá ser realizado em grupo de no máximo cinco (5) alunos e no mínimo três 
(3) alunos. 
A cena deverá contemplar: 
 Uso de Iluminação; 
 Coloração com realismo (usar tabela de cores); 
 Interação com o usuário (teclado e/ou mouse); 
 Uso de animação; 
 Aplicação de textura; 
 Animação no cenário – telas de Start, Jogo e Stop; 
Os itens abaixo serão levados em consideração na avaliação do Trabalho: 
 Criatividade e Realismo; 
 Lógica do jogo; 
 Interação; 
 Animação. 
3. Entrega 
A entrega será feita pelo ULife, através de um arquivo compactado no formato ZIP, onde 
haverá o projeto (pasta src) contendo os arquivos .java. 
4. Apresentação/Demonstração 
A apresentação e demonstração serão realizadas nos dias 04 e 05/12. 
