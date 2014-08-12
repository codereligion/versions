package com.codereligion.versions;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;

import javax.annotation.concurrent.Immutable;
import java.util.Iterator;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Iterables.elementsEqual;

@Immutable
public final class BuildMetadata implements Tuple<Name> {

    private static final BuildMetadata EMPTY = new BuildMetadata();
    
    private final ImmutableList<Name> identifiers;

    private BuildMetadata() {
        this.identifiers = ImmutableList.of();
    }
    
    private BuildMetadata(ImmutableList<Name> identifiers) {
        this.identifiers = identifiers;
    }

    @Override
    public Iterator<Name> iterator() {
        return identifiers.iterator();
    }

    @Override
    public boolean isEmpty() {
        return identifiers.isEmpty();
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifiers);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof BuildMetadata) {
            return elementsEqual(this, (BuildMetadata) that);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return Joiner.on('.').join(identifiers);
    }

    public static BuildMetadata empty() {
        return EMPTY;
    }

    public static BuildMetadata parse(final String metadata) {
        checkNotNull(metadata, "BuildMetadata");
        
        if (metadata.isEmpty()) {
            return empty();
        }

        return valueOf(Splitter.on('.').split(metadata));
    }
    
    public static BuildMetadata valueOf(final Iterable<String> values) {
        checkNotNull(values, "Values");
        final FluentIterable<Name> names = from(values).transform(toName());
        return new BuildMetadata(names.toList());
    }

    private static Function<String, Name> toName() {
        return new Function<String, Name>() {
            @Override
            public Name apply(final String value) {
                return Name.valueOf(value);
            }
        };
    }
    
}