package biz;

import java.util.ArrayList;

/**
 * 検索文字列作成処理
 */

public class Create2gram {

    /**
     * 引数の文字列をn-gramで分割する。
     * 今回のケースはn=2で固定。
     * ex :入力文字列が「あいうえお」の場合、「あい」「いう」「うえ」「えお」の4つに分割する
     *
     * @param inputStr String
     *            入力された項目
     * @return result ArrayList<String>
     *            分割した文字列の配列
     */
    public static ArrayList<String> split2gram(String inputStr) {

        ArrayList<String> result = new ArrayList<>();

        // 一文字の場合はそのまま返す
        if(inputStr.length() == 1){
            result.add(inputStr);
            return result;
        }
        // 文字列に対してポインタを先頭から1文字ずつずらして分割する。
        // 周回数はtextの文字列長 - 1となる。
        int numberOfLoop = inputStr.length() - 1;
        for (int i = 0; i < numberOfLoop; i++) {
            result.add(inputStr.substring(i, i + 2));
        }
        return result;
    }
}
