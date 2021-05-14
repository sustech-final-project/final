package Games.清淞大佬.pve;


import java.util.Random;

    public class Robotplayer {
        private Station[][] currentArray;
        private static int X;
        private static int Y;
        private static int tot;
        private boolean play = true;
        private int blockAround;
        private int blankAreaAround;
        private int totalAreaAround;
        private int blockFoundAround;
        private double[][] Matrix;
        private static int currentRow;
        private static int currentCol;
        private static double eps = 1e-10;
        private static boolean result;
        private static int steps;

        public Robotplayer(int X, int Y) {
            this.X = X;
            this.Y = Y;
            steps = 0;
            currentArray = new Station[X + 1][Y + 1];
            for(int i = 1; i <= X; ++i) {
                for(int j = 1; j <= Y; ++j) {
                    currentArray[i][j] = Station.unknown;
                }
            }
            while(play == true) {
                robotplay();
                steps++;
            }
            this.result = mainWin.getInstance().panel_block.getResult();
        }
        public void robotplay() {
            play = mainWin.getInstance().panel_block.isPlaying();
            if(play == false) {
                return;
            }
            currentArray = mainWin.getInstance().panel_block.getCurrentArray();
            if(bruteForce() == false) {
                if(GaussLiner() == false) {
                    randomPick();
                    //		System.out.println("xixi");
                }
            }
        }
        private boolean GaussLiner() {
            tot = X * Y;
            Matrix = new double[tot + 1][tot + 2];
            for(int i = 1; i <= tot; ++i) {
                for(int j = 1; j <= tot + 1; ++j) {
                    Matrix[i][j] = 0.0;
                }
            }
            for(int i = 1; i <= X; ++i) {
                for(int j = 1; j <= Y; ++j) {
                    if(currentArray[i][j] == Station.block || currentArray[i][j] == Station.unknown || currentArray[i][j] == Station.zero) {
                        continue;
                    }
                    currentRow = (i - 1) * Y + j;
                    getBlockAround(i, j);
                    if(i > 1 && j > 1) {
                        if(currentArray[i - 1][j - 1] == Station.unknown) {
                            currentCol = (i - 2) * Y + j - 1;
                            Matrix[currentRow][currentCol] = 1.0;
                        }
                        if(currentArray[i - 1][j - 1] == Station.block) {
                            blockAround--;
                        }
                    }
                    if(i > 1) {
                        if(currentArray[i - 1][j] == Station.unknown) {
                            currentCol = (i - 2) * Y + j;
                            Matrix[currentRow][currentCol] = 1.0;
                        }
                        if(currentArray[i - 1][j] == Station.block) {
                            blockAround--;
                        }
                    }
                    if(i > 1 && j < Y) {
                        if(currentArray[i - 1][j + 1] == Station.unknown) {
                            currentCol = (i - 2) * Y + j + 1;
                            Matrix[currentRow][currentCol] = 1.0;
                        }
                        if(currentArray[i - 1][j + 1] == Station.block) {
                            blockAround--;
                        }
                    }
                    if(j > 1) {
                        if(currentArray[i][j - 1] == Station.unknown) {
                            currentCol = (i - 1) * Y + j - 1;
                            Matrix[currentRow][currentCol] = 1.0;
                        }
                        if(currentArray[i][j - 1] == Station.block) {
                            blockAround--;
                        }
                    }
                    if(j < Y) {
                        if(currentArray[i][j + 1] == Station.unknown) {
                            currentCol = (i - 1) * Y + j + 1;
                            Matrix[currentRow][currentCol] = 1.0;
                        }
                        if(currentArray[i][j + 1] == Station.block) {
                            blockAround--;
                        }
                    }
                    if(i < X && j > 1) {
                        if(currentArray[i + 1][j - 1] == Station.unknown) {
                            currentCol = i * Y + j - 1;
                            Matrix[currentRow][currentCol] = 1.0;
                        }
                        if(currentArray[i + 1][j - 1] == Station.block) {
                            blockAround--;
                        }
                    }
                    if(i < X) {
                        if(currentArray[i + 1][j] == Station.unknown) {
                            currentCol = i * Y + j;
                            Matrix[currentRow][currentCol] = 1.0;
                        }
                        if(currentArray[i + 1][j] == Station.block) {
                            blockAround--;
                        }
                    }
                    if(i < X && j < Y) {
                        if(currentArray[i + 1][j + 1] == Station.unknown) {
                            currentCol = i * Y + j + 1;
                            Matrix[currentRow][currentCol] = 1.0;
                        }
                        if(currentArray[i + 1][j + 1] == Station.block) {
                            blockAround--;
                        }
                    }
                    Matrix[currentRow][tot + 1] = (double)blockAround;
                }
            }
            int cur, pos = 1;
            for(int i = 1; i <= tot; ++i) {
                for(int j = pos; j <= tot; ++j) {
                    if(Math.abs(Matrix[j][i]) > eps) {
                        for(int k = i; k <= tot + 1; ++k) {
                            double tmp = Matrix[j][k];
                            Matrix[j][k] = Matrix[pos][k];
                            Matrix[pos][k] = tmp;
                        }
                        break;
                    }
                }
                if(Math.abs(Matrix[pos][i]) < eps) {
                    //		pos++;
                    continue;
                }
                for(int j = 1; j <= tot; ++j) {
                    if(j != pos && Math.abs(Matrix[j][i]) > eps) {
                        double tmp = Matrix[j][i] / Matrix[pos][i];
                        for(int k = i; k <= tot + 1; ++k) {
                            Matrix[j][k] -= tmp * Matrix[pos][k];
                        }
                    }
                }
                pos++;
            }
            for(int i = 1; i <= tot; ++i) {
                cur = 0;
                for(int j = 1; j <= tot; ++j) {
                    if(Math.abs(Matrix[i][j]) > eps) {
                        cur++;
                        pos = j;
                    }
                }
                //find an answer
//			if(cur == 1) {
//				System.out.println("find");
//				currentRow = pos / Y + 1;
//				currentCol = pos % Y;
//				if(currentCol == 0) {
//					currentRow--;
//					currentCol = Y;
//				}
//				if(Math.abs(Matrix[i][tot + 1] - Matrix[i][pos]) < eps) {
//					mainWin.getInstance().panel_block.solveRightButtonEvents(currentRow, currentCol);
//					return true;
//				}
//				else if(Math.abs(Matrix[i][tot + 1]) < eps){
//					mainWin.getInstance().panel_block.solveLeftButtonEvents(currentRow, currentCol);
//					return true;
//				}
//			}

                //That answer should be either 0 or 1 can be used to find answer
                double positiveN = 0;
                double negativeN = 0;
                for(int j = 1; j <= tot; ++j) {
                    if(Matrix[i][j] > eps) {
                        positiveN += Matrix[i][j];
                    }
                    if(Matrix[i][j] < -eps) {
                        negativeN += Matrix[i][j];
                    }
                }
                for(int j = 1; j <= tot; ++j) {
                    currentRow = j / Y + 1;
                    currentCol = j % Y;
                    if(currentCol == 0) {
                        currentRow--;
                        currentCol = Y;
                    }
                    if(Matrix[i][j] > eps) {
                        // let it be 0, find it can not be 0, so it must be 1
                        if(positiveN - Matrix[i][j] - Matrix[i][tot + 1] < -eps) {
                            mainWin.getInstance().panel_block.solveRightButtonEvents(currentRow, currentCol);
                            //	System.out.println(positiveN + " " + negativeN + " " + Matrix[i][tot + 1] + " find1");
                            return true;
                        }
                        // let it be 1, find it can not be 1, so it must be 0
                        if(Matrix[i][j] + negativeN - Matrix[i][tot + 1] > eps) {
                            mainWin.getInstance().panel_block.solveLeftButtonEvents(currentRow, currentCol);
                            //	System.out.println(positiveN + " " + negativeN + " " + Matrix[i][tot + 1] + " find2");
                            return true;
                        }
                    }
                    if(Matrix[i][j] < -eps) {
                        // let it be 0, find it can not be 0, so it must be 1
                        if(negativeN - Matrix[i][j] - Matrix[i][tot + 1] > eps) {
                            mainWin.getInstance().panel_block.solveRightButtonEvents(currentRow, currentCol);
                            //	System.out.println(positiveN + " " + negativeN + " " + Matrix[i][tot + 1] + " find3");
                            return true;
                        }
                        // let it be 1, find it can not be 1, so it must be 0
                        if(Matrix[i][j] + positiveN - Matrix[i][tot + 1] < -eps) {
                            mainWin.getInstance().panel_block.solveLeftButtonEvents(currentRow, currentCol);
                            //	System.out.println(positiveN + " " + negativeN + " " + Matrix[i][tot + 1] + " find4");
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        private void randomPick() {
            Random blockCreator = new Random();
            int newX, newY;
            while(true) {
                newX = blockCreator.nextInt(X) + 1;
                newY = blockCreator.nextInt(Y) + 1;
                if(currentArray[newX][newY] == Station.unknown) {
                    Leftsolution(newX, newY);
                    break;
                }
            }
        }
        private boolean bruteForce() {
            for(int i = 1; i <= X; ++i) {
                for(int j = 1; j <= Y; ++j) {
                    if(currentArray[i][j] == Station.unknown || currentArray[i][j] == Station.block || currentArray[i][j] == Station.zero) {
                        continue;
                    }
                    if(tryClick(i, j)) {
                        return true;
                    }
                }
            }
            return false;
        }
        private boolean tryClick(int x, int y) {
            getBlockAround(x, y);
            totalAreaAround = 0;
            blankAreaAround = 0;
            blockFoundAround = 0;
            if(x - 1 >= 1 && y - 1 >= 1) {
                query(x - 1, y - 1);
            }
            if(x - 1 >= 1) {
                query(x - 1, y);
            }
            if(x - 1 >= 1 && y + 1 <= Y) {
                query(x - 1, y + 1);
            }
            if(y - 1 >= 1) {
                query(x, y - 1);
            }
            if(y + 1 <= Y) {
                query(x, y + 1);
            }
            if(x + 1 <= X && y - 1 >= 1) {
                query(x + 1, y - 1);
            }
            if(x + 1 <= X) {
                query(x + 1, y);
            }
            if(x + 1 <= X && y + 1 <= Y) {
                query(x + 1, y + 1);
            }
            if(blankAreaAround == 0) {
                return false;
            }
            if(blankAreaAround == blockAround - blockFoundAround) {
                if(canClick(x - 1, y - 1)) {
                    Rightsolution(x - 1, y - 1);
                    return true;
                }
                if(canClick(x - 1, y)) {
                    Rightsolution(x - 1, y);
                    return true;
                }
                if(canClick(x - 1, y + 1)) {
                    Rightsolution(x - 1, y + 1);
                    return true;
                }
                if(canClick(x, y - 1)) {
                    Rightsolution(x, y - 1);
                    return true;
                }
                if(canClick(x, y + 1)) {
                    Rightsolution(x, y + 1);
                    return true;
                }
                if(canClick(x + 1, y - 1)) {
                    Rightsolution(x + 1, y - 1);
                    return true;
                }
                if(canClick(x + 1, y)) {
                    Rightsolution(x + 1, y);
                    return true;
                }
                if(canClick(x + 1, y + 1)) {
                    Rightsolution(x + 1, y + 1);
                    return true;
                }
            }
            if(blockAround == blockFoundAround) {
                if(canClick(x - 1, y - 1)) {
                    Leftsolution(x - 1, y - 1);
                    return true;
                }
                if(canClick(x - 1, y)) {
                    Leftsolution(x - 1, y);
                    return true;
                }
                if(canClick(x - 1, y + 1)) {
                    Leftsolution(x - 1, y + 1);
                    return true;
                }
                if(canClick(x, y - 1)) {
                    Leftsolution(x, y - 1);
                    return true;
                }
                if(canClick(x, y + 1)) {
                    Leftsolution(x, y + 1);
                    return true;
                }
                if(canClick(x + 1, y - 1)) {
                    Leftsolution(x + 1, y - 1);
                    return true;
                }
                if(canClick(x + 1, y)) {
                    Leftsolution(x + 1, y);
                    return true;
                }
                if(canClick(x + 1, y + 1)) {
                    Leftsolution(x + 1, y + 1);
                    return true;
                }
            }
            return false;
        }
        public int getSteps() {
            return this.steps;
        }
        private boolean canClick(int x, int y) {
            if(x >= 1 && y >= 1 && x <= X && y <= Y) {
                if(currentArray[x][y] == Station.unknown) {
                    return true;
                }
            }
            return false;
        }
        private void query(int x, int y) {
            totalAreaAround++;
            if(currentArray[x][y] == Station.unknown) {
                blankAreaAround++;
            }
            if(currentArray[x][y] == Station.block) {
                blockFoundAround++;
            }
        }
        private void Leftsolution(int x, int y) {
            mainWin.getInstance().panel_block.solveLeftButtonEvents(x, y);
        }
        private void Rightsolution(int x, int y) {
            mainWin.getInstance().panel_block.solveRightButtonEvents(x, y);
        }
        private void getBlockAround(int x, int y) {
            switch(currentArray[x][y]) {
                case one:
                    blockAround = 1;
                    break;
                case two:
                    blockAround = 2;
                    break;
                case three:
                    blockAround = 3;
                    break;
                case four:
                    blockAround = 4;
                    break;
                case five:
                    blockAround = 5;
                    break;
                case six:
                    blockAround = 6;
                    break;
                case seven:
                    blockAround = 7;
                    break;
                default:
                    System.out.println("Error");
                    break;
            }
        }
        public boolean getResult() {
            return this.result;
        }
    }

