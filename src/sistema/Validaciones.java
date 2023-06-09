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

            /** Manejo cada dígito de la cédula como un char, el 0 tiene valor 48 en los indices
             *  de char, por lo que si le resto 48 me da el valor numérico, si pasa de 9 ya no es
             *  válido, pues el char sería otra cosa*/

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

    public static boolean validarTelefono(String telefono) throws Exception{
        if (telefono.length() != 10){
            throw new Exception("Recuerde que el teléfono debe tener 10 dígitos,\n" +
                                "ejemplo: \"0996693539\"");
        }

        if(telefono.charAt(0) != '0' || telefono.charAt(1) != '9'){
            throw new Exception("Recuerde que el número debe empezar por \"09\"");
        }

        for (int i = 1; i < 10; i++){
            int valor = telefono.charAt(i) - 48;
            if (valor < 0 || valor > 9){
                throw new Exception("Recuerde que todos los dígitos del teléfono deben ser números");
            }
        }
        return true;
    }
}
