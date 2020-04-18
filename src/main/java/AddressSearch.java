import biz.CreateIndex;
import biz.CsvRead;
import biz.FindAddress;
import entity.Address;

import java.util.*;

/**
 * 住所検索アプリケーション
 *
 * 全体概要
 * ・KEN_ALL.CSVから住所情報を読み込む。同じ住所情報でも複数行にまたがるものは結合する。
 * ・indexは住所情報に付与するIDと、「郵便番号」「都道府県」「市区町村」「その他住所」をn-gram(n=2固定)で分割した文字列をMapで保持する
 * ・コンソールから検索文字列を入力させ、indexを使用した検索を実施し、住所情報リストを返却する
 * ・入力された検索文字列はn-gram(n=2固定)で分割して検索に使用する
 * ・住所情報は「郵便番号」「都道府県」「市区町村」「その他住所」をcsv方式で作成する
 */
public class AddressSearch {
    public static void main(String[] args) {
        // 起動してからcsv読み込みの間のラグを気にさせないための注釈文言を入れる
        System.out.println("起動中...");

        List<Address> addressList = CsvRead.csvReadAndMakeAddressList();
        Map<String, List<Integer>> indexMap = CreateIndex.createIndex(addressList);

        // 入力が出来るようになったことを明示的に出力しておく
        System.out.println("検索する地名を入力してください▼");
        Scanner sc = new Scanner(System.in);

        // スペースは除去
        String inputStr = sc.nextLine().replace(" ", "").replace("　", "");

        List<Address> searchResultAddressList = FindAddress.find(inputStr, indexMap, addressList);
        output(searchResultAddressList);

        // Scannerは閉じておく
        sc.close();
    }

  /**
   * 最終出力処理
   * 責務分割のため外出ししておく
   *
   * @param addressList
   *            住所情報リスト
   */
    private static void output(List<Address> addressList){
        for(Address address : addressList){
            System.out.println(
                address.getPostCode() + "," +
                address.getPrefecture() + "," +
                address.getCity() + "," +
                address.getTownArea());
        }

    }

}
