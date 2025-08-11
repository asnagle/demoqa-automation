package converters;

public class NoOpConverter implements ExcelValueConverter{
	@Override
    public Object convert(String value) {
        return value;
    }

}
