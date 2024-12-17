package a02b.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CursorHelpersImpl implements CursorHelpers{

    public class CursorImpl<X> implements Cursor<X>{

        private List<X> list = new ArrayList<>();
        private int index;

        CursorImpl(List<X> list) {
            this.list.addAll(list);
            this.index = 0;
        }

        @Override
        public X getElement() {
            return this.list.get(index);
        }

        @Override
        public boolean advance() {
            if(this.list.size() > index + 1) {
                this.index++;
                return true;
            }
            return false;
        }
    }

    @Override
    public <X> Cursor<X> fromNonEmptyList(List<X> list) {
        return new CursorImpl<>(list);
    }

    @Override
    public Cursor<Integer> naturals() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'naturals'");
    }

    @Override
    public <X> Cursor<X> take(Cursor<X> input, int max) {
        List<X> ret =  this.toList(input, max);
        return new CursorImpl<>(ret);
    }

    @Override
    public <X> void forEach(Cursor<X> input, Consumer<X> consumer) {
        consumer.accept(input.getElement());
        while(input.advance()){
            consumer.accept(input.getElement());
        }
    }

    @Override
    public <X> List<X> toList(Cursor<X> input, int max) {
        int count = 1;
        List<X> list = new ArrayList<>();
        list.add(input.getElement());
        while(input.advance() && count < max){
            list.add(input.getElement());
            count++;
        }
        return list;
    }
}
