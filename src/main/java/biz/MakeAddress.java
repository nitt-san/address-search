package biz;

import entity.Address;

/**
 * 住所情報作成処理
 */

public class MakeAddress {

    /**
     * csvの1Lineから必要な情報を抜き出して住所情報を作成する。
     * columns[2]が郵便番号、columns[6]が都道府県、columns[7]が市区町村、
     * columns[8]がその他住所に該当する。
     * input前に必要項目だけセットして渡すか悩んだが、逆に複雑になるのと、メモリ消費もそれほど変わらないのでそのまま渡す
     *
     * @param columns
     *            csvの1Lineを,区切りで保持した文字列の配列
     * @return result
     *            住所情報
     */
    public static Address makeAddress(int indexId, String[] columns){
        Address result = Address.createAddressIndex();
        result.setAddressId(indexId);
        result.setPostCode(columns[2]);
        result.setPrefecture(columns[6]);
        result.setCity(columns[7]);
        result.setTownArea(columns[8]);
        return result;
    }
}
