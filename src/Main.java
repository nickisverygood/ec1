import java.util.Scanner;

import static java.lang.Double.valueOf;
import static java.lang.Math.abs;

public class Main {
    enum TriangleType {
        EQUILATERAL, ISOSCELES, SCALENE,NOTATRIG
    }
    public static void main(String[] args) {
        System.out.println("Hello World!");


        Main.GeometricObject.Triangle g = new Main().new GeometricObject().new Triangle();
        g.fillInData();
        g.printProperties();
    }



    class GeometricObject{
        double perimeter;
        double sideLength [];
        String color;
        boolean filled;
        double area;
        //                                                             perimeter sideLength color filled     area
        protected boolean[] statisticsCheckList = {false, false, false, false, false}; //status of {lowest, highest, avg} being calculated


        public void fillInData(){
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter color");
            setColor(sc.next());
            System.out.println("Enter filled");
            setFilled(Boolean.valueOf(sc.next()));
            sc.close();
        }
        public double getPerimeter() {
            setPerimeter();
            return perimeter;
        }

        void setPerimeter() {
            if(statisticsCheckList[1]){
                perimeter = 0;
                for(int i=0;i<sideLength.length;i++){
                    perimeter += sideLength[i];
                }
                statisticsCheckList[0] = true;
            }
        }

        public double[] getSideLength() {
            return sideLength;
        }

        public void setSideLength(double[] sideLength) {
            this.sideLength = sideLength;
            statisticsCheckList[1] = true;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
            statisticsCheckList[2] = true;
        }

        public boolean getFilled() {
            return filled;
        }

        public void setFilled(boolean filled) {
            this.filled = filled;
            statisticsCheckList[3] = true;
        }

        public double getArea() {
            return area;
        }


        public void calculate(){
            if(statisticsCheckList[1]){
                setPerimeter();
            }
        }

        public void printProperties(){
            calculate();
            if (operatorANDtoArray(statisticsCheckList)) {
                System.out.print("\nArea: " + String.valueOf(getArea()));
                System.out.print("\nPerimeter: " + String.valueOf(getPerimeter()));
                System.out.print("\nColor: " + String.valueOf(getColor()));
                System.out.print("\nFilled: " + String.valueOf(getFilled()));
            }else {
                System.out.print("\nERROR!");
            }
        }



        boolean operatorANDtoArray(boolean[] toapplyAND) {
            boolean toReturn = toapplyAND[0];
            if (toapplyAND.length > 1) {
                for (int i = 1; i < toapplyAND.length; i++) {
                    toReturn = toReturn && toapplyAND[i];
                }
            }
            return toReturn;

        }



        class Triangle extends GeometricObject{
            TriangleType triangleType ;
            @Override
            public void fillInData() {
                Scanner sc=new Scanner(System.in);
                System.out.println("Enter sideLength 1");
                double side1 = Double.valueOf(sc.next());
                System.out.println("Enter sideLength 2");
                double side2 = Double.valueOf(sc.next());
                System.out.println("Enter sideLength 3");
                double side3 = Double.valueOf(sc.next());
                triangleType = classify(side1,side2,side3);
                if(triangleType!= TriangleType.NOTATRIG) setSideLength(new double[]{side1,side2,side3}) ;else {System.out.println("Warning! This is not a valid triangle!");};
                System.out.println("Enter color");
                setColor(sc.next());
                System.out.println("Enter filled");
                setFilled(Boolean.valueOf(sc.next()));
                sc.close();
            }
            TriangleType classify(double a, double b, double c) {
                if (a <= 0 || b <= 0 || c <= 0) return TriangleType.NOTATRIG; // added test
                if (a == b && b == c) return TriangleType.EQUILATERAL;
                if (a >= b+c || c >= b+a || b >= a+c) return TriangleType.NOTATRIG;
                if (b==c || a==b || c==a) return TriangleType.ISOSCELES;
                return TriangleType.SCALENE;
            }

            private void setArea() {
                if (statisticsCheckList[0] ){
                    area =trigArea(getSideLength());
                    statisticsCheckList[4] = true;
                }
            }

            private double trigArea(double[] sideLengths){
                double s = 0.5 * (sideLengths[0]+sideLengths[1]+sideLengths[2]);
                return Math.sqrt(s*abs(s-sideLengths[0])*abs(s-sideLengths[1])*abs(s-sideLengths[2]));
            }

            public TriangleType getTriangleType() {
                return triangleType;
            }

            public void setTriangleType(TriangleType triangleType) {
                this.triangleType = triangleType;
            }

            @Override
            public void calculate() {
                if(statisticsCheckList[1]){
                    setPerimeter();
                    if(statisticsCheckList[0]) setArea();
                }
            }

            @Override
            public void printProperties() {
                calculate();
                if (operatorANDtoArray(statisticsCheckList)) {
                    System.out.print("\nType: " + String.valueOf(getTriangleType().name()));
                    System.out.print("\nArea: " + String.valueOf(getArea()));
                    System.out.print("\nPerimeter: " + String.valueOf(getPerimeter()));
                    System.out.print("\nColor: " + String.valueOf(getColor()));
                    System.out.print("\nFilled: " + String.valueOf(getFilled()));
                }else {
                    System.out.print("\nERROR!");
                }
            }
        }


    }


}


