package neo4j;

public interface CanBeStored {
    CanBeStored create();

    CanBeStored update();

    CanBeStored delete();

    CanBeStored get(long var1);
}
