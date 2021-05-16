package Gamesdgy大佬.pve;


import java.util.Random;

public class boomInitialization {

        protected Station[][] Array;
        protected int[] blockX;
        protected int[] blockY;
        protected static int blockNum;
        protected static int currentBlockNum;
        private static int blockAround;
        private static int newX;
        private static int newY;
        public boomInitialization(int X, int Y, int N) {
            blockNum = 0;
            currentBlockNum = 0;
            Array = new Station[X + 1][Y + 1];
            blockX = new int[N];
            blockY = new int[N];
            blockNum = N;

            for(int i = 1; i <= X; ++i) {
                for(int j = 1; j <= Y; ++j) {
                    Array[i][j] = Station.zero;
                }
            }

            Random blockCreator = new Random();
            while(currentBlockNum < blockNum) {
                newX = blockCreator.nextInt(X) + 1;
                newY = blockCreator.nextInt(Y) + 1;
                if(Judge(newX, newY)){
                    Array[newX][newY] = Station.block;
                    blockX[currentBlockNum] = newX;
                    blockY[currentBlockNum] = newY;
                    currentBlockNum++;
                }
            }
            //counting numbers
            for(int i = 1; i <= X; ++i) {
                for(int j = 1; j <= Y; ++j) {
                    if(Array[i][j] != Station.block) {
                        blockAround = 0;
                        if(i - 1 >= 1 && j - 1 >= 1) {
                            if(Array[i - 1][j - 1] == Station.block) {
                                blockAround++;
                            }
                        }
                        if(i - 1 >= 1) {
                            if(Array[i - 1][j] == Station.block) {
                                blockAround++;
                            }
                        }
                        if(i - 1 >= 1 && j + 1 <= Y) {
                            if(Array[i - 1][j + 1] == Station.block) {
                                blockAround++;
                            }
                        }
                        if(j - 1 >= 1) {
                            if(Array[i][j - 1] == Station.block) {
                                blockAround++;
                            }
                        }
                        if(j + 1 <= Y) {
                            if(Array[i][j + 1] == Station.block) {
                                blockAround++;
                            }
                        }
                        if(i + 1 <= X && j - 1 >= 1) {
                            if(Array[i + 1][j - 1] == Station.block) {
                                blockAround++;
                            }
                        }
                        if(i + 1 <= X) {
                            if(Array[i + 1][j] == Station.block) {
                                blockAround++;
                            }
                        }
                        if(i + 1 <= X && j + 1 <= Y) {
                            if(Array[i + 1][j + 1] == Station.block) {
                                blockAround++;
                            }
                        }
                        switch (blockAround) {
                            case 0:
                                Array[i][j] = Station.zero;
                                break;
                            case 1:
                                Array[i][j] = Station.one;
                                break;
                            case 2:
                                Array[i][j] = Station.two;
                                break;
                            case 3:
                                Array[i][j] = Station.three;
                                break;
                            case 4:
                                Array[i][j] = Station.four;
                                break;
                            case 5:
                                Array[i][j] = Station.five;
                                break;
                            case 6:
                                Array[i][j] = Station.six;
                                break;
                            case 7:
                                Array[i][j] = Station.seven;
                                break;
                        }
                    }
                }
            }
        }
        private boolean Judge(int X, int Y) {
            if(Array[X][Y] == Station.block) {
                return false;
            }
            return true;
        }
    }

