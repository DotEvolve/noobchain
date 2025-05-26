package net.dotevolve.base.structure;

public interface IConvertor<ConvertFrom, ConvertTo> {

    ConvertTo convert(ConvertFrom convertFrom);
}
