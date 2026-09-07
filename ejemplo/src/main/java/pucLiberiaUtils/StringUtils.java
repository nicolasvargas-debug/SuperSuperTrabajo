/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pucLiberiaUtils;

/**
 *
 * @author nikol
 */
public class StringUtils {
    public static String formatearCadena(String cadena, int n) {
        int faltan;
        if (cadena != null && !cadena.isEmpty() && cadena.length() > n) {
            return cadena.substring(0, n);
        } else {
            faltan = n - cadena.length();
            return cadena + " ".repeat(faltan);
        }
    }
}
