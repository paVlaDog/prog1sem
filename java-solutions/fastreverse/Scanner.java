import java.io.*;

public class Scanner{
    private Reader in = null;
    int bufferSize = 0, index;
    String OS = System.getProperty("os.name");
    char[] buffer = new char[1024*8];

    //Конструкторы:
    //Первый - для потоков ввода. (Проверено).
    //Второй - для файлов. (Проверено).
    //Третий - для строк. (Проверено).

    public Scanner (InputStream in) throws IOException {
        this.in = new BufferedReader(new InputStreamReader(in));
        bufferFill();
    }

    public Scanner (String nameFile, String encoding) 
    throws UnsupportedEncodingException, FileNotFoundException, IOException {
        this.in = new BufferedReader(new InputStreamReader(new FileInputStream(nameFile), encoding));
        bufferFill();
    }

    public Scanner (String str) {
        this.bufferSize = str.length();
        this.buffer = str.toCharArray();
    }

    //Private методы:

    //Пополнение буфера.

    private void bufferFill () throws IOException {
        index = 0;
        bufferSize = in.read(buffer);
    }

    //Проверка, является ли символ разделителем. Разделитель зависит от ключа.

    private boolean checkSeparator(char ch, String key) throws IOException {
        boolean sep = false;
        if (key == " ") {
            sep = Character.isWhitespace(ch);
        }
        if (key == "LN") {
            if (OS.indexOf("mac") >= 0) {
                sep = ch == '\r';
            } else {
                sep = ch == '\n';
            }
        }
        if (key == "Word") {
            sep = !(Character.isLetter(ch) || Character.getType(ch) == Character.DASH_PUNCTUATION || ch == '\'');
        }
        if (key == "hasLN") {
            sep = false;
        }
        return sep;
    }

    //Public методы:

    //Проверки на наличие следующего символа.
    //hasNextToken - Получает ключ-разделитель, пропускает разделительные символы.
    //Возвращает true, если нашёл любой неразделительный символ.

    private boolean hasNextToken(String key) throws IOException {
        if (bufferSize == -1) {
            return false;
        }
        for (; index < bufferSize; index++) { 
            if (!checkSeparator(buffer[index], key)) {
                return true;
            }
        }
        if (in == null) {                                  //Костыль для String
            return false;
        }
        bufferFill();
        return hasNextToken(key);
    }

    //hasNext - Ключ-разделитель - любой пробельный символ.

    public boolean hasNext() throws IOException {
        return hasNextToken(" ");
    }

    //hasNextWords - Ключ-разделитель - символы, не используемые в словах.

    public boolean hasNextWord() throws IOException {
        return hasNextToken("Word");
    }

    //hasNextLine - Ключ-разделитель - отсутствует. (true если есть хоть один несчитанный символ)

    public boolean hasNextLine() throws IOException {
        return hasNextToken("hasLN");
    }

    //hasNextWordInLine() - Ключ-разделитель - не Word символы. Проверяет есть ли токены в этой строке.

    public boolean hasNextWordInLine() throws IOException {
        if (bufferSize == -1) {
            return false;
        }
        for (; index < bufferSize; index++) { 
            if (!checkSeparator(buffer[index], "Word")) {
                return true;
            }
            if (checkSeparator(buffer[index], "LN")) {
                return false;
            }
        }
        if (in == null) {                                  //Костыль для String
            return false;
        }
        bufferFill();
        return hasNextWordInLine();
    }


    //Получение следующего токена, между указанными разделителями. Key - ключ разделителя.
    //nextToken пропускает все символы-разделители.
    //Возвращает все символы между указанными разделителями.

    private String nextToken (String key, String oldToken) throws IOException {
        StringBuilder token = new StringBuilder(oldToken);
        for (; index < bufferSize; index++) {
            if (!checkSeparator(buffer[index], key) || key == "LN" || !"".equals(oldToken)) {
                break;
            }
        }
        for (; index < bufferSize; index++) {
            if (checkSeparator(buffer[index], key)) {
                index++;
                return token.toString();
            }
            token.append(buffer[index]);
        }
        if (in != null && bufferSize != -1) {
            bufferFill();
            return nextToken(key, token.toString());
        }else{
            return token.toString();
        }
    }

    //next - Ключ-разделитель - любой пробельный символ.

    public String next() throws IOException {
        return nextToken(" ", "");
    }

    //nextWords - Ключ-разделитель - символы, не используемые в словах. - .

    public String nextWord() throws IOException {
        return nextToken("Word", "");
    }

    //nextIntHex - Ключ-разделитель - любой пробельный символ.
    //Возвращает любое число в Hex

    public String nextIntHex() throws IOException {
        String token = nextToken(" ", "");
        if (token.length() > 2 && token.charAt(0) == '0' && (token.charAt(1) == 'x' || token.charAt(1) == 'X')) {
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

    //Закрытие потока ввода

    public void close() throws IOException {
        if(in != null) {
            in.close();
        }
    }

    //Переход на следующую строку. Работает в связке с hasNextWordInLine.

    public boolean toNextLine() throws IOException {
        boolean flagFinish1 = false, flagFinish2 = false;
        if (bufferSize != -1 && in != null) {
            for (; index < bufferSize; index++) { 
                if (flagFinish1 && !checkSeparator(buffer[index], "LN")) {
                    flagFinish2 = true;
                }
                if (checkSeparator(buffer[index], "LN")) {
                    flagFinish1 = true;
                }
                if (flagFinish2) {
                    return true;
                }
            }
            bufferFill();
            return toNextLine();
        } else {
            return false;
        }
    }
}