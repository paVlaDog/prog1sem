import java.io.*;
import java.lang.Character;
import java.util.Arrays;

public class FastScanner{
    private BufferedReader in = null;
    int bufferSize = 0, index;
    char[] buffer = new char[1024*3];

    //Конструкторы:
    //Первый - для потоков ввода. (Проверено).
    //Второй - для файлов. (Проверено).
    //Третий - для строк. (Проверено).

    public FastScanner (InputStream in) throws IOException {
        this.in = new BufferedReader(new InputStreamReader(in));
        bufferFill();
    }

    public FastScanner (String nameFile, String encoding) throws UnsupportedEncodingException, FileNotFoundException, IOException {
        this.in = new BufferedReader(new InputStreamReader(new FileInputStream(nameFile), encoding));
        bufferFill();
    }

    public FastScanner (String str) {
        this.bufferSize = str.length();
        this.buffer = str.toCharArray();
    }

    //Private методы:

    //Пополнение буфера (/r и /n всегда будут в одной строке).

    private void bufferFill () throws IOException {
        index = 0;
        bufferSize = in.read(buffer);
        if (bufferSize > 0 && buffer[bufferSize - 1] == '\r') {
            int sym = in.read();
            if (sym != -1) {
                bufferSize++;
                buffer = Arrays.copyOf(buffer, bufferSize);
                buffer[bufferSize - 1] = (char)sym;
            } 
        }
    }

    //Проверка, является ли символ разделителем. Разделитель зависит от ключа.

    private boolean checkSeparator(char ch, String key) throws IOException {
        boolean sep = false;
        if (key == "LN"){
            sep = ch == '\n' || ch == '\r';
            if (ch == '\r' && buffer[index + 1] == '\n'){
                index++;
            }
        }
        if (key == "hasLN"){
            sep = false;
        }
        return sep;
    }


    //hasNext - Ключ-разделитель - любой пробельный символ.

    public boolean hasNext() throws IOException {
        if (bufferSize == -1) {
            return false;
        }
        for (; index < bufferSize; index++) { 
            if (!Character.isWhitespace(buffer[index])) {
                return true;
            }
        }
        if (in == null) {                                  //Костыль для String
            return false;
        }
        bufferFill();
        return hasNext();
    }

    //hasNextLine - Ключ-разделитель - отсутствует. (true если есть хоть один несчитанный символ)

    public boolean hasNextLine() throws IOException {
        if (bufferSize == -1) {
            return false;
        }
        if (index < bufferSize) { 
                return true;
        }
        if (in == null) {                                  //Костыль для String
            return false;
        }
        bufferFill();
        return hasNextLine();
    }

    //Получение следующего токена, между указанными разделителями. Key - ключ разделителя.
    //nextToken пропускает все символы-разделители.
    //Возвращает все символы между указанными разделителями.

    private String nextToken (String key, String oldToken) throws IOException{
        StringBuilder token = new StringBuilder();
        while(true){
            for (; index < bufferSize; index++) {
                if (checkSeparator(buffer[index], key)) {
                    index++;
                    return token.toString();
                }
                token.append(buffer[index]);
            }
            if (in != null && bufferSize != -1) {
                bufferFill();
            }else{
                return token.toString();
            }
        }
    }

    private String nextTokenForIntHex (String oldToken) throws IOException{
        StringBuilder token = new StringBuilder();
        for (; index < bufferSize; index++) {
            if (!Character.isWhitespace(buffer[index]) || !"".equals(oldToken)) {
                break;
            }
        }
        for (; index < bufferSize; index++) {
            if (Character.isWhitespace(buffer[index])) {
                index++;
                return token.toString();
            }
            token.append(buffer[index]);
        }
        if (in != null && bufferSize != -1) {
            bufferFill();
            return nextTokenForIntHex(token.toString());
        }else{
            return token.toString();
        }
    }

    //nextIntHex - Ключ-разделитель - любой пробельный символ.
    //Возвращает любое число в Hex

    public String nextIntHex() throws IOException {
        String token = nextTokenForIntHex("");
        if (token.length() > 2 && token.charAt(0) == '0' && (token.charAt(1) == 'x' || token.charAt(1) == 'X')){
            return token;
        }else{
            token = "0x" + Integer.toHexString(Integer.parseInt(token));
            return token;
        }
    }

    //nextLine - Ключ-разделитель - перевод строки.

    public String nextLine() throws IOException {
        return nextToken("LN", "");
    }

    public void close() throws IOException {
        if(in != null) {
            in.close();
        }
    }
}