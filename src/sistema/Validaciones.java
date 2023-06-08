package sistema;

public class Validaciones {
    /**
     * Método para validar la cédula
     * @param cedula cédula a validar
     * @return true: es válida, false: es inválida
     * @throws Exception envia un mensaje con el tipo de error, si es que no hay solo
     * números o no son 10 digitos
     */
    public static boolean cedulaValida(String cedula) throws Exception{
        if (cedula.length() != 10){
            throw new Exception("La cédula debe contener 10 caracteres numéricos.");
        }

        int sumaDigitos = 0;

        /**
         * Verificación del digito final de la cédula y que haya solo números
         */
        for (int i = 0; i < 9; i++){
            int digito = cedula.charAt(i) - 48;
            if(digito < 0 || digito > 9 ){
                throw new Exception("La cédula debe contener solo caracteres numéricos.");
            }
            if (i%2 == 0){
                if (2 * digito > 9){
                    sumaDigitos += 2 * digito - 9;
                } else {
                    sumaDigitos += 2 * digito;
                }
            } else {
                sumaDigitos += digito;
            }
        }

        /**
         * Validación final del último digito de la cédula
         * !!! Revisar
         */
        int digitoV = 10 - sumaDigitos%10;
        if(digitoV == 10){
            return cedula.charAt(9) - 48 == 0;
        }
        if ((cedula.charAt(9) - 48) == digitoV){
            return true;
        }
        return false;
    }
}
