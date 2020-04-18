package biz;

import entity.Address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * index作成処理
 */
public class CreateIndex {

    /**
     * 住所情報から検索用indexを作成する
     *・住所情報IDと、「郵便番号」「都道府県」「市区町村」「その他住所」をn-gram(n=2固定)で分割した文字列をMapで返却する
     *
     *   @param addressList List<Address>
     *              住所情報リスト
     *   @return result Map<String, List<Integer>>
     *              検索用index
     */
    public static Map<String, List<Integer>> createIndex(List<Address> addressList){
        Map<String, List<Integer>> result = new HashMap<>();

        for(Address address : addressList){
            // 住所情報の「郵便番号」「都道府県」「市区町村」「その他住所」を2gramで分解してindexを作成する。
            // この時、"は不要情報なので削除する
            List<String> twoGramList = new ArrayList<>();
            twoGramList.addAll(Create2gram.split2gram(address.getTownArea().replace("\"", "")));
            twoGramList.addAll(Create2gram.split2gram(address.getCity().replace("\"", "")));
            twoGramList.addAll(Create2gram.split2gram(address.getPrefecture().replace("\"", "")));

            // このタイミングでユニークにしておく
            List<String> twoGramUniqueList = twoGramList.stream().distinct().collect(Collectors.toList());
            for(String twoGram : twoGramUniqueList){
                if (!result.containsKey(twoGram)){
                    result.put(twoGram, new ArrayList<>());
                }
                result.get(twoGram).add(address.getAddressId());
            }
        }

        return result;
    }
}
