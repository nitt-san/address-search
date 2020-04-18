package biz;

import entity.Address;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * CSV読み込み処理
 */

public class CsvRead {

    /**
     * CSVを読み込み、住所情報リストを作成する
     *・KEN_ALL.CSVから読み込んで検索用のindexを作成する
     *・今回は外部ファイルを読むのではなく、jar上にまとめて組み込んでおく
     *・KEN_ALL.CSVはソース内でShift-JISで読み込むことを明示的に記載する。無加工ファイルが前提のため。
     *・その他情報に折り返しが存在するため、以下条件を全て満たす場合にはその他情報を文字列連結する。
     *    - 郵便番号が連続している
     *    - csvの行数が早い方のその他住所が30文字以上ある
     *
     *   @return result ArrayList<Address>
     *              住所情報リスト
     */
    public static ArrayList<Address> csvReadAndMakeAddressList(){

        // 対象csvを定義
        File file = new File("src/main/resources/KEN_ALL.CSV");

        ArrayList<Address> result = new ArrayList<>();
        try {
            // ファイル読み込みセット一式を宣言。Shift-JISじゃないと文字化けするので注意
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file), "Shift-JIS"));
            int indexId = 0;
            String line;
            // csv全行を回す
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                // 前周でセットした住所Entityの郵便番号と同一
                // かつ前周でセットした住所Entityのその他住所が30文字以上あった場合、
                // 次の周の項目に折り返しが発生しているので、その他住所と検索用index文字列を繋ぎ込む
                if(result.size() != 0
                        && columns[2].contains(result.get(result.size() - 1).getPostCode())
                        && result.get(result.size() - 1).getTownArea().length() >= 30){
                    Address continueIndex;
                    continueIndex = result.get(result.size() - 1);

                    // その他住所は元の文字列の最後の文字(")と、繋ぐ文字列の最初の文字(")を削って繋ぐ
                    StringBuilder townAreaSb = new StringBuilder(continueIndex.getTownArea());
                    townAreaSb.delete(townAreaSb.length() - 1, townAreaSb.length());
                    townAreaSb.append(columns[8].substring(1));
                    continueIndex.setTownArea(townAreaSb.toString());
                    // 最終項目を置き換える
                    result.set(result.size() - 1, continueIndex);
                } else {
                    result.add(MakeAddress.makeAddress(indexId, columns));
                    indexId++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
