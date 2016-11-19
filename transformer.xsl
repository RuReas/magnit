<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <entries>
            <xsl:apply-templates/>
        </entries>
    </xsl:template>
    <xsl:template match="entry">
        <entry>
            <xsl:attribute name="field">
                <xsl:value-of select="normalize-space()"/>
            </xsl:attribute>
        </entry>
    </xsl:template>
</xsl:stylesheet> 
