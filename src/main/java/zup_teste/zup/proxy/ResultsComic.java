package zup_teste.zup.proxy;

import java.util.List;

public class ResultsComic {
    private Integer id;
    private String title;
    private String isbn;
    private String description;
    private List<PricesComic> prices;
    private CreatorsComic creators;


    public ResultsComic(Integer id, String title, String isbn, String description, List<PricesComic> prices, CreatorsComic creators) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.description = description;
        this.prices = prices;
        this.creators = creators;
    }


    public ResultsComic() {
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PricesComic> getPrices() {
        return this.prices;
    }

    public void setPrices(List<PricesComic> prices) {
        this.prices = prices;
    }

    public CreatorsComic getCreators() {
        return this.creators;
    }

    public void setCreators(CreatorsComic creators) {
        this.creators = creators;
    }


   

}
