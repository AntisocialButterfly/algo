package uva706;

import org.junit.Test;

public class LcdDisplayTest {
  @Test
  public void test1() {
    Main.LcdDisplay lcdDisplay = new Main.LcdDisplay(1);
    System.out.println(lcdDisplay.print("1234567890"));
  }

  @Test
  public void test2() {
    Main.LcdDisplay lcdDisplay = new Main.LcdDisplay(2);
    System.out.println(lcdDisplay.print("1234567890"));
  }

  @Test
  public void test3() {
    Main.LcdDisplay lcdDisplay = new Main.LcdDisplay(3);
    System.out.println(lcdDisplay.print("1234567890"));
  }

  @Test
  public void test4() {
    Main.LcdDisplay lcdDisplay = new Main.LcdDisplay(4);
    System.out.println(lcdDisplay.print("1234567890"));
  }

  @Test
  public void test5() {
    Main.LcdDisplay lcdDisplay = new Main.LcdDisplay(5);
    System.out.println(lcdDisplay.print("1234567890"));
  }
}