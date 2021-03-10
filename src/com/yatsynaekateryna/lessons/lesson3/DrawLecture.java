package com.yatsynaekateryna.lessons.lesson3;

public class DrawLecture {
    public static void main(String[] args) {

        if (false) {

            int h = 600;
            int w = 800;
            double x = (double) w / (double) h;
            double y = 0;
            int[][] pixels = new int[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (j > y && i > h / 2) {
                        pixels[i][j] = 0x00FF00;
                    } else if (j > y && i < h / 2) {
                        pixels[i][j] = 0xFFFFFF;
                    } else {
                        pixels[i][j] = 0xFF0000;
                    }
                }
                if (i < h / 2) {
                    y += x;
                } else {
                    y -= x;
                }
            }
            ImageUtil.getImageFromPixels(pixels);
        }

        if (false) {
            int h = 600;
            int w = 800;
            int r = 150;
            int centre_i = h / 2;
            int centre_j = w / 2;
            int color = 0;
            int[][] pixels = new int[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (color > 0) {
                        pixels[i][j] = 0xFF0000;
                        color--;
                    } else {
                        double rad = Math.sqrt(Math.pow(centre_i - i, 2) + Math.pow(centre_j - j, 2));
                        if ((int) rad == r) {
                            pixels[i][j] = 0xFF0000;
                            color = (centre_j - j) * 2;
                        } else {
                            pixels[i][j] = 0xFFFFFF;
                        }
                    }
                }

            }
            ImageUtil.getImageFromPixels(pixels);
        }

        //7. Перевернуть картинку слева направо.
        if (false) {
            int[][] pictureTurnLeftToRight = ImageUtil.getPixelsFromImage("picture.jpg");
            for (int i = 0; i < pictureTurnLeftToRight.length - 1; i++) {
                for (int j = 0, k = pictureTurnLeftToRight[i].length - 1; j < k; j++, k--) {
                    pictureTurnLeftToRight[i][j] += pictureTurnLeftToRight[i][k];
                    pictureTurnLeftToRight[i][k] = pictureTurnLeftToRight[i][j] - pictureTurnLeftToRight[i][k];
                    pictureTurnLeftToRight[i][j] = pictureTurnLeftToRight[i][j] - pictureTurnLeftToRight[i][k];
                }
            }
            ImageUtil.getImageFromPixels(pictureTurnLeftToRight);
        }

        //8. Повернуть картинку на угол 90 градусов.
        if (true) {
            int[][] originalPicture = ImageUtil.getPixelsFromImage("picture.jpg");
            int[][] pictureRotate90Degrees = new int[originalPicture[0].length][originalPicture.length];
            for (int i = 0; i < originalPicture.length - 1; i++) {
                for (int j = 0, k = originalPicture[i].length - 1; j < originalPicture[i].length - 1; j++, k--) {
                    pictureRotate90Degrees[k][i] = originalPicture[i][j];
                }
            }
            ImageUtil.getImageFromPixels(pictureRotate90Degrees);
        }
    }
}
