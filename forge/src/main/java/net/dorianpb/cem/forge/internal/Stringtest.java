package net.dorianpb.cem.forge.internal;

import net.dorianpb.cem.forge.internal.util.CemStringParser;


/***/
public class Stringtest {
  public static void main(String[] args) {
    String yeet = "clamp(-0.5 * alex.rx, 0, 90)";
    CemStringParser.ParsedExpression betelgeuse = CemStringParser.parse(yeet, null, null);
    System.out.println(betelgeuse.eval(0, 0, 0, 0, 0, null, null));
  }
}