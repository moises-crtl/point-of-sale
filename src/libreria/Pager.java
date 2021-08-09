package libreria;

import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author ioriyagamy
 * @param <T>
 */
public class Pager<T> {
    
    private final List<T> _dataList;
    private final JLabel label;
    private static int maxReg, regForPage, pageAcount, numPage = 1;

    public Pager(List<T> dataList, JLabel label, int regForPage) {
        this._dataList = dataList;
        this.label = label;
        this.regForPage = regForPage;
        loadDatas();
    }

    private void loadDatas() {
                
        numPage = 1;
        maxReg = _dataList.size();
        pageAcount = (maxReg / regForPage);
        if((maxReg % regForPage) > 0){
            pageAcount += 1;
        }
        label.setText("Paginas 1/" + pageAcount);
    }
    
    public int firstPage(){
        numPage = 1;
        label.setText("Paginas" + numPage + "/" + pageAcount);
        return numPage;
    }
    
    public int previousPage(){
        if(numPage > 1){
            numPage -= 1;
            label.setText("Paginas" + numPage + "/" + pageAcount);
        }
        return numPage;
    }
    
    public int nextPage(){
        if(numPage == pageAcount){
            numPage -= 1;
        }
        if(numPage < pageAcount){
            numPage += 1;
            label.setText("Paginas" + numPage + "/" + pageAcount);
        }
        return numPage;
    }
    
    public int last(){
        numPage = pageAcount;
        label.setText("Paginas" + numPage + "/" + pageAcount);
        return numPage;
    }
}
