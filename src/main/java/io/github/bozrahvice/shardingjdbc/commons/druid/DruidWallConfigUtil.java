package io.github.bozrahvice.shardingjdbc.commons.druid;

import com.alibaba.druid.wall.WallConfig;
import org.springframework.util.StringUtils;

/**
 * @author ylpanda
 * @since 1.0.0
 */
public final class DruidWallConfigUtil {

    /**
     * 根据当前的配置 druid防火墙配置
     *
     * @param c 当前配置
     * @return 防火墙配置
     */
    public static WallConfig toWallConfig(DruidWallConfig c) {
        WallConfig wallConfig = new WallConfig();

        String tempDir = c.getDir();
        if (!StringUtils.isEmpty(tempDir)) {
            wallConfig.loadConfig(tempDir);
        }
        String tempTenantTablePattern = c.getTenantTablePattern();
        if (!StringUtils.isEmpty(tempTenantTablePattern)) {
            wallConfig.setTenantTablePattern(tempTenantTablePattern);
        }
        String tempTenantColumn = c.getTenantColumn();
        if (!StringUtils.isEmpty(tempTenantColumn)) {
            wallConfig.setTenantTablePattern(tempTenantColumn);
        }
        Boolean tempNoneBaseStatementAllow = c.getNoneBaseStatementAllow();
        if (tempNoneBaseStatementAllow != null && tempNoneBaseStatementAllow) {
            wallConfig.setNoneBaseStatementAllow(true);
        }
        Integer tempInsertValuesCheckSize = c.getInsertValuesCheckSize();
        if (tempInsertValuesCheckSize != null) {
            wallConfig.setInsertValuesCheckSize(tempInsertValuesCheckSize);
        }
        Integer tempSelectLimit = c.getSelectLimit();
        if (tempSelectLimit != null) {
            c.setSelectLimit(tempSelectLimit);
        }

        Boolean tempCallAllow = c.getCallAllow();
        if (tempCallAllow != null && !tempCallAllow) {
            wallConfig.setCallAllow(false);
        }
        Boolean tempSelectAllow = c.getSelectAllow();
        if (tempSelectAllow != null && !tempSelectAllow) {
            wallConfig.setSelelctAllow(false);
        }
        Boolean tempSelectIntoAllow = c.getSelectIntoAllow();
        if (tempSelectIntoAllow != null && !tempSelectIntoAllow) {
            wallConfig.setSelectIntoAllow(false);
        }
        Boolean tempSelectIntoOutfileAllow = c.getSelectIntoOutfileAllow();
        if (tempSelectIntoOutfileAllow != null && tempSelectIntoOutfileAllow) {
            wallConfig.setSelectIntoOutfileAllow(true);
        }
        Boolean tempSelectWhereAlwayTrueCheck = c.getSelectWhereAlwayTrueCheck();
        if (tempSelectWhereAlwayTrueCheck != null && !tempSelectWhereAlwayTrueCheck) {
            wallConfig.setSelectWhereAlwayTrueCheck(false);
        }
        Boolean tempSelectHavingAlwayTrueCheck = c.getSelectHavingAlwayTrueCheck();
        if (tempSelectHavingAlwayTrueCheck != null && !tempSelectHavingAlwayTrueCheck) {
            wallConfig.setSelectHavingAlwayTrueCheck(false);
        }
        Boolean tempSelectUnionCheck = c.getSelectUnionCheck();
        if (tempSelectUnionCheck != null && !tempSelectUnionCheck) {
            wallConfig.setSelectUnionCheck(false);
        }
        Boolean tempSelectMinusCheck = c.getSelectMinusCheck();
        if (tempSelectMinusCheck != null && !tempSelectMinusCheck) {
            wallConfig.setSelectMinusCheck(false);
        }
        Boolean tempSelectExceptCheck = c.getSelectExceptCheck();
        if (tempSelectExceptCheck != null && !tempSelectExceptCheck) {
            wallConfig.setSelectExceptCheck(false);
        }
        Boolean tempSelectIntersectCheck = c.getSelectIntersectCheck();
        if (tempSelectIntersectCheck != null && !tempSelectIntersectCheck) {
            wallConfig.setSelectIntersectCheck(false);
        }
        Boolean tempCreateTableAllow = c.getCreateTableAllow();
        if (tempCreateTableAllow != null && !tempCreateTableAllow) {
            wallConfig.setCreateTableAllow(false);
        }
        Boolean tempDropTableAllow = c.getDropTableAllow();
        if (tempDropTableAllow != null && !tempDropTableAllow) {
            wallConfig.setDropTableAllow(false);
        }
        Boolean tempAlterTableAllow = c.getAlterTableAllow();
        if (tempAlterTableAllow != null && !tempAlterTableAllow) {
            wallConfig.setAlterTableAllow(false);
        }
        Boolean tempRenameTableAllow = c.getRenameTableAllow();
        if (tempRenameTableAllow != null && !tempRenameTableAllow) {
            wallConfig.setRenameTableAllow(false);
        }
        Boolean tempHintAllow = c.getHintAllow();
        if (tempHintAllow != null && !tempHintAllow) {
            wallConfig.setHintAllow(false);
        }
        Boolean tempLockTableAllow = c.getLockTableAllow();
        if (tempLockTableAllow != null && !tempLockTableAllow) {
            wallConfig.setLockTableAllow(false);
        }
        Boolean tempStartTransactionAllow = c.getStartTransactionAllow();
        if (tempStartTransactionAllow != null && !tempStartTransactionAllow) {
            wallConfig.setStartTransactionAllow(false);
        }
        Boolean tempBlockAllow = c.getBlockAllow();
        if (tempBlockAllow != null && !tempBlockAllow) {
            wallConfig.setBlockAllow(false);
        }
        Boolean tempConditionAndAlwayTrueAllow = c.getConditionAndAlwayTrueAllow();
        if (tempConditionAndAlwayTrueAllow != null && tempConditionAndAlwayTrueAllow) {
            wallConfig.setConditionAndAlwayTrueAllow(true);
        }
        Boolean tempConditionAndAlwayFalseAllow = c.getConditionAndAlwayFalseAllow();
        if (tempConditionAndAlwayFalseAllow != null && tempConditionAndAlwayFalseAllow) {
            wallConfig.setConditionAndAlwayFalseAllow(true);
        }
        Boolean tempConditionDoubleConstAllow = c.getConditionDoubleConstAllow();
        if (tempConditionDoubleConstAllow != null && tempConditionDoubleConstAllow) {
            wallConfig.setConditionDoubleConstAllow(true);
        }
        Boolean tempConditionLikeTrueAllow = c.getConditionLikeTrueAllow();
        if (tempConditionLikeTrueAllow != null && !tempConditionLikeTrueAllow) {
            wallConfig.setConditionLikeTrueAllow(false);
        }
        Boolean tempSelectAllColumnAllow = c.getSelectAllColumnAllow();
        if (tempSelectAllColumnAllow != null && !tempSelectAllColumnAllow) {
            wallConfig.setSelectAllColumnAllow(false);
        }
        Boolean tempDeleteAllow = c.getDeleteAllow();
        if (tempDeleteAllow != null && !tempDeleteAllow) {
            wallConfig.setDeleteAllow(false);
        }
        Boolean tempDeleteWhereAlwayTrueCheck =
                c.getDeleteWhereAlwayTrueCheck();
        if (tempDeleteWhereAlwayTrueCheck != null && !tempDeleteWhereAlwayTrueCheck) {
            wallConfig.setDeleteWhereAlwayTrueCheck(false);
        }
        Boolean tempDeleteWhereNoneCheck = c.getDeleteWhereNoneCheck();
        if (tempDeleteWhereNoneCheck != null && tempDeleteWhereNoneCheck) {
            wallConfig.setDeleteWhereNoneCheck(true);
        }
        Boolean tempUpdateAllow = c.getUpdateAllow();
        if (tempUpdateAllow != null && !tempUpdateAllow) {
            wallConfig.setUpdateAllow(false);
        }
        Boolean tempUpdateWhereAlayTrueCheck =
                c.getUpdateWhereAlayTrueCheck();
        if (tempUpdateWhereAlayTrueCheck != null && !tempUpdateWhereAlayTrueCheck) {
            wallConfig.setUpdateWhereAlayTrueCheck(false);
        }
        Boolean tempUpdateWhereNoneCheck = c.getUpdateWhereNoneCheck();
        if (tempUpdateWhereNoneCheck != null && tempUpdateWhereNoneCheck) {
            wallConfig.setUpdateWhereNoneCheck(true);
        }
        Boolean tempInsertAllow = c.getInsertAllow();
        if (tempInsertAllow != null && !tempInsertAllow) {
            wallConfig.setInsertAllow(false);
        }
        Boolean tempMergeAllow = c.getMergeAllow();
        if (tempMergeAllow != null && !tempMergeAllow) {
            wallConfig.setMergeAllow(false);
        }
        Boolean tempMinusAllow = c.getMinusAllow();
        if (tempMinusAllow != null && !tempMinusAllow) {
            wallConfig.setMinusAllow(false);
        }
        Boolean tempIntersectAllow = c.getIntersectAllow();
        if (tempIntersectAllow != null && !tempIntersectAllow) {
            wallConfig.setIntersectAllow(false);
        }
        Boolean tempReplaceAllow = c.getReplaceAllow();
        if (tempReplaceAllow != null && !tempReplaceAllow) {
            wallConfig.setReplaceAllow(false);
        }
        Boolean tempSetAllow = c.getSetAllow();
        if (tempSetAllow != null && !tempSetAllow) {
            wallConfig.setSetAllow(false);
        }
        Boolean tempCommitAllow = c.getCommitAllow();
        if (tempCommitAllow != null && !tempCommitAllow) {
            wallConfig.setCommitAllow(false);
        }
        Boolean tempRollbackAllow = c.getRollbackAllow();
        if (tempRollbackAllow != null && !tempRollbackAllow) {
            wallConfig.setRollbackAllow(false);
        }
        Boolean tempUseAllow = c.getUseAllow();
        if (tempUseAllow != null && !tempUseAllow) {
            wallConfig.setUseAllow(false);
        }
        Boolean tempMultiStatementAllow = c.getMultiStatementAllow();
        if (tempMultiStatementAllow != null && tempMultiStatementAllow) {
            wallConfig.setMultiStatementAllow(true);
        }
        Boolean tempTruncateAllow = c.getTruncateAllow();
        if (tempTruncateAllow != null && !tempTruncateAllow) {
            wallConfig.setTruncateAllow(false);
        }
        Boolean tempCommentAllow = c.getCommentAllow();
        if (tempCommentAllow != null && tempCommentAllow) {
            wallConfig.setCommentAllow(true);
        }
        Boolean tempStrictSyntaxCheck = c.getStrictSyntaxCheck();
        if (tempStrictSyntaxCheck != null && !tempStrictSyntaxCheck) {
            wallConfig.setStrictSyntaxCheck(false);
        }
        Boolean tempConstArithmeticAllow = c.getConstArithmeticAllow();
        if (tempConstArithmeticAllow != null && !tempConstArithmeticAllow) {
            wallConfig.setConstArithmeticAllow(false);
        }
        Boolean tempLimitZeroAllow = c.getLimitZeroAllow();
        if (tempLimitZeroAllow != null && tempLimitZeroAllow) {
            wallConfig.setLimitZeroAllow(true);
        }
        Boolean tempDescribeAllow = c.getDescribeAllow();
        if (tempDescribeAllow != null && !tempDescribeAllow) {
            wallConfig.setDescribeAllow(false);
        }
        Boolean tempShowAllow = c.getShowAllow();
        if (tempShowAllow != null && !tempShowAllow) {
            wallConfig.setShowAllow(false);
        }
        Boolean tempSchemaCheck = c.getSchemaCheck();
        if (tempSchemaCheck != null && !tempSchemaCheck) {
            wallConfig.setSchemaCheck(false);
        }
        Boolean tempTableCheck = c.getTableCheck();
        if (tempTableCheck != null && !tempTableCheck) {
            wallConfig.setTableCheck(false);
        }
        Boolean tempFunctionCheck = c.getFunctionCheck();
        if (tempFunctionCheck != null && !tempFunctionCheck) {
            wallConfig.setFunctionCheck(false);
        }
        Boolean tempObjectCheck = c.getObjectCheck();
        if (tempObjectCheck != null && !tempObjectCheck) {
            wallConfig.setObjectCheck(false);
        }
        Boolean tempVariantCheck = c.getVariantCheck();
        if (tempVariantCheck != null && !tempVariantCheck) {
            wallConfig.setVariantCheck(false);
        }
        Boolean tempMustParameterized = c.getMustParameterized();
        if (tempMustParameterized != null && tempMustParameterized) {
            wallConfig.setMustParameterized(true);
        }
        Boolean tempDoPrivilegedAllow = c.getDoPrivilegedAllow();
        if (tempDoPrivilegedAllow != null && tempDoPrivilegedAllow) {
            wallConfig.setDoPrivilegedAllow(true);
        }
        Boolean tempWrapAllow = c.getWrapAllow();
        if (tempWrapAllow != null && !tempWrapAllow) {
            wallConfig.setWrapAllow(false);
        }
        Boolean tempMetadataAllow = c.getMetadataAllow();
        if (tempMetadataAllow != null && !tempMetadataAllow) {
            wallConfig.setMetadataAllow(false);
        }
        Boolean tempConditionOpXorAllow = c.getConditionOpXorAllow();
        if (tempConditionOpXorAllow != null && tempConditionOpXorAllow) {
            wallConfig.setConditionOpXorAllow(true);
        }
        Boolean tempConditionOpBitwseAllow = c.getConditionOpBitwseAllow();
        if (tempConditionOpBitwseAllow != null && !tempConditionOpBitwseAllow) {
            wallConfig.setConditionOpBitwseAllow(false);
        }
        Boolean tempCaseConditionConstAllow = c.getCaseConditionConstAllow();
        if (tempCaseConditionConstAllow != null && tempCaseConditionConstAllow) {
            wallConfig.setCaseConditionConstAllow(true);
        }
        Boolean tempCompleteInsertValuesCheck = c.getCompleteInsertValuesCheck();
        if (tempCompleteInsertValuesCheck != null && tempCompleteInsertValuesCheck) {
            wallConfig.setCompleteInsertValuesCheck(true);
        }
        return wallConfig;
    }
}
