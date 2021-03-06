package com.codereligion.severus;

import static com.google.common.base.Preconditions.checkNotNull;

public final class VersionBuilder {
    
    private VersionNumber major = VersionNumber.valueOf(0);
    private VersionNumber minor = VersionNumber.valueOf(0);
    private VersionNumber patch = VersionNumber.valueOf(0);
    private PreReleaseVersion preRelease = PreReleaseVersion.empty();
    private BuildMetadata build = BuildMetadata.empty();
    private VersionPrecedence precedence = VersionPrecedence.NATURAL;
    
    VersionBuilder() {
        // should only be accessible from static factory method
    }

    public VersionBuilder copy(Version version) throws VersionFormatException {
        checkNotNull(version, "Version");

        major(version.getMajor());
        minor(version.getMinor());
        patch(version.getPatch());
        preRelease(version.getPreRelease());
        build(version.getBuild());
        precedence(version.getPrecedence());
        
        return this;
    }

    public VersionBuilder major(long major) {
        return major(VersionNumber.valueOf(major));
    }
    
    public VersionBuilder major(String major) {
        return major(VersionNumber.valueOf(major));
    }
    
    public VersionBuilder major(VersionNumber major) {
        this.major = checkNotNull(major, "Major");
        return this;
    }
    
    public VersionBuilder minor(long minor) {
        return minor(VersionNumber.valueOf(minor));
    }
    
    public VersionBuilder minor(String minor) {
        return minor(VersionNumber.valueOf(minor));
    }
    
    public VersionBuilder minor(VersionNumber minor) {
        this.minor = checkNotNull(minor, "Minor");
        return this;
    }
    
    public VersionBuilder patch(long patch) {
        return patch(VersionNumber.valueOf(patch));
    }
    
    public VersionBuilder patch(String patch) {
        return patch(VersionNumber.valueOf(patch));
    }
    
    public VersionBuilder patch(VersionNumber patch) {
        this.patch = checkNotNull(patch, "Patch");
        return this;
    }
    
    public VersionBuilder preRelease(String version) {
        return preRelease(PreReleaseVersion.valueOf(version));
    }
    
    public VersionBuilder preRelease(PreReleaseVersion preRelease) {
        this.preRelease = checkNotNull(preRelease, "PreRelease");
        return this;
    }
    
    public VersionBuilder build(String build) {
        return build(BuildMetadata.valueOf(build));
    }
    
    public VersionBuilder build(BuildMetadata build) {
        this.build = checkNotNull(build, "BuildMetadata");
        return this;
    }
    
    public VersionBuilder precedence(VersionPrecedence precedence) {
        this.precedence = precedence;
        return this;
    }
    
    public Version create() {
        return new ConcreteVersion(major, minor, patch, preRelease, build, precedence);
    }
    
}
