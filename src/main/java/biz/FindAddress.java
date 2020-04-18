package biz;

import entity.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 住所検索処理
 */
public class FindAddress {

    /**
     * 住所情報から検索を行う
     *・検索文字列をn-gram(n=2)で分割し、実際に検索に使用する文字列群を確定する
     *・全ての住所情報の検索用index文字列から検索文字列でマッチングを行い、リストに保存する
     *・返却するリストはユニークにする
     *
     *   @param inputStr String
     *              検索文字列
     *   @param indexMap Map<String, List<Integer>>
     *              検索用index
     *   @param addressList List<Address>
     *              検索対象となる住所情報
     *   @return result List<Address>
     *              検索結果を詰めた住所情報リスト
     */
    public static List<Address> find(String inputStr, Map<String, List<Integer>> indexMap, List<Address> addressList){

        List<Address> result = new ArrayList<>();

        // 検索文字列をn-gram(n=2)で分割し、実際に検索に使用する文字列群を確定する
        List<String> searchWordList = Create2gram.split2gram(inputStr);
        List<Integer> hitAddressId = new ArrayList<>();
        // indexに対して、検索文字列でマッチングを行う
        for(String searchWord : searchWordList){
            if(indexMap.containsKey(searchWord)){
                hitAddressId.addAll(indexMap.get(searchWord));
            }
        }

        // indexの検索結果リストはここでユニークにしておく
        List<Integer> uniqueAddressIdList = hitAddressId.stream().distinct().collect(Collectors.toList());

        for(Address address : addressList){
            for(Integer indexId : uniqueAddressIdList){
                if(indexId.equals(address.getAddressId())){
                    result.add(address);
                }
            }
        }

        return result;
    }

}
