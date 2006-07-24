package examples.teeda.web.grid;

public class GridPage {

    private FooItem[] fooItems;

    public FooItem[] getFooItems() {
        if (fooItems == null) {
            fooItems = new FooItem[7];
            fooItems[0] = createItem("a1", "b1", "c1", "d1", "e1", "f1");
            fooItems[1] = createItem("a2", "b2", "c2", "d2", "e2", "f2");
            fooItems[2] = createItem("a3", "b3", "c3", "d3", "e3", "f3");
            fooItems[3] = createItem("a4", "b4", "c4", "d4", "e4", "f4");
            fooItems[4] = createItem("a5", "b5", "c5", "d5", "e5", "f5");
            fooItems[5] = createItem("a6", "b6", "c6", "d6", "e6", "f6");
            fooItems[6] = createItem("a7", "b7", "c7", "d7", "e7", "f7");
        } else {
            FooItem[] newItems = new FooItem[7];
            newItems[0] = createItem("a1", "b1", "c1", fooItems[0].getDdd(),
                "e1", "f1");
            newItems[1] = createItem("a2", "b2", "c2", fooItems[1].getDdd(),
                "e2", "f2");
            newItems[2] = createItem("a3", "b3", "c3", fooItems[2].getDdd(),
                "e3", "f3");
            newItems[3] = createItem("a4", "b4", "c4", fooItems[3].getDdd(),
                "e4", "f4");
            newItems[4] = createItem("a5", "b5", "c5", fooItems[4].getDdd(),
                "e5", "f5");
            newItems[5] = createItem("a6", "b6", "c6", fooItems[5].getDdd(),
                "e6", "f6");
            newItems[6] = createItem("a7", "b7", "c7", fooItems[6].getDdd(),
                "e7", "f7");
            fooItems = newItems;
        }
        return fooItems;
    }

    private FooItem createItem(String aaa, String bbb, String ccc, String ddd,
        String eee, String fff) {
        final FooItem item = new FooItem();
        item.setAaa(aaa);
        item.setBbb(bbb);
        item.setCcc(ccc);
        item.setDdd(ddd);
        item.setEee(eee);
        item.setFff(fff);
        return item;
    }

    public void setFooItems(FooItem[] fooItems) {
        this.fooItems = fooItems;
    }

    public static class FooItem {

        private String aaa;
        private String bbb;
        private String ccc;
        private String ddd;
        private String eee;
        private String fff;

        public String getBbb() {
            return bbb;
        }

        public void setBbb(String bar) {
            this.bbb = bar;
        }

        public String getAaa() {
            return aaa;
        }

        public void setAaa(String foo) {
            this.aaa = foo;
        }

        public String getCcc() {
            return ccc;
        }

        public void setCcc(String ccc) {
            this.ccc = ccc;
        }

        public String getDdd() {
            return ddd;
        }

        public void setDdd(String ddd) {
            this.ddd = ddd;
        }

        public String getEee() {
            return eee;
        }

        public void setEee(String eee) {
            this.eee = eee;
        }

        public String getFff() {
            return fff;
        }

        public void setFff(String fff) {
            this.fff = fff;
        }
    }

    private String aaa;
    private String bbb;
    private String ccc;
    private String ddd;
    private String eee;
    private String fff;

    public String getBbb() {
        return bbb;
    }

    public void setBbb(String bar) {
        this.bbb = bar;
    }

    public String getAaa() {
        return aaa;
    }

    public void setAaa(String foo) {
        this.aaa = foo;
    }

    public String getCcc() {
        return ccc;
    }

    public void setCcc(String ccc) {
        this.ccc = ccc;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getEee() {
        return eee;
    }

    public void setEee(String eee) {
        this.eee = eee;
    }

    public String getFff() {
        return fff;
    }

    public void setFff(String fff) {
        this.fff = fff;
    }

}