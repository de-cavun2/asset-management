package decavun2.security;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Stream.concat;
import static ua.com.fielden.platform.types.tuples.T2.t2;
import static ua.com.fielden.platform.utils.CollectionUtil.mapOf;
import static ua.com.fielden.platform.utils.CollectionUtil.setOf;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import decavun2.security.tokens.AppAdminModuleToken;
import decavun2.security.tokens.UsersAndPersonnelModuleToken;
import ua.com.fielden.platform.security.ISecurityToken;
import ua.com.fielden.platform.security.provider.ISecurityTokenNodeTransformation;
import ua.com.fielden.platform.security.provider.SecurityTokenNode;
import ua.com.fielden.platform.security.tokens.attachment.AttachmentDownload_CanExecute_Token;
import ua.com.fielden.platform.security.tokens.attachment.Attachment_CanDelete_Token;
import ua.com.fielden.platform.security.tokens.attachment.Attachment_CanReadModel_Token;
import ua.com.fielden.platform.security.tokens.attachment.Attachment_CanRead_Token;
import ua.com.fielden.platform.security.tokens.attachment.Attachment_CanSave_Token;
import ua.com.fielden.platform.security.tokens.open_simple_master.AttachmentMaster_CanOpen_Token;
import ua.com.fielden.platform.security.tokens.open_simple_master.DashboardRefreshFrequencyMaster_CanOpen_Token;
import ua.com.fielden.platform.security.tokens.open_simple_master.UserMaster_CanOpen_Token;
import ua.com.fielden.platform.security.tokens.open_simple_master.UserRoleMaster_CanOpen_Token;
import ua.com.fielden.platform.security.tokens.persistent.DashboardRefreshFrequencyUnit_CanReadModel_Token;
import ua.com.fielden.platform.security.tokens.persistent.DashboardRefreshFrequencyUnit_CanRead_Token;
import ua.com.fielden.platform.security.tokens.persistent.DashboardRefreshFrequency_CanDelete_Token;
import ua.com.fielden.platform.security.tokens.persistent.DashboardRefreshFrequency_CanReadModel_Token;
import ua.com.fielden.platform.security.tokens.persistent.DashboardRefreshFrequency_CanRead_Token;
import ua.com.fielden.platform.security.tokens.persistent.DashboardRefreshFrequency_CanSave_Token;
import ua.com.fielden.platform.security.tokens.persistent.KeyNumber_CanReadModel_Token;
import ua.com.fielden.platform.security.tokens.persistent.KeyNumber_CanRead_Token;
import ua.com.fielden.platform.security.tokens.synthetic.DomainExplorer_CanReadModel_Token;
import ua.com.fielden.platform.security.tokens.synthetic.DomainExplorer_CanRead_Token;
import ua.com.fielden.platform.security.tokens.user.UserAndRoleAssociation_CanReadModel_Token;
import ua.com.fielden.platform.security.tokens.user.UserAndRoleAssociation_CanRead_Token;
import ua.com.fielden.platform.security.tokens.user.UserRoleTokensUpdater_CanExecute_Token;
import ua.com.fielden.platform.security.tokens.user.UserRole_CanDelete_Token;
import ua.com.fielden.platform.security.tokens.user.UserRole_CanReadModel_Token;
import ua.com.fielden.platform.security.tokens.user.UserRole_CanRead_Token;
import ua.com.fielden.platform.security.tokens.user.UserRole_CanSave_Token;
import ua.com.fielden.platform.security.tokens.user.UserRolesUpdater_CanExecute_Token;
import ua.com.fielden.platform.security.tokens.user.User_CanDelete_Token;
import ua.com.fielden.platform.security.tokens.user.User_CanReadModel_Token;
import ua.com.fielden.platform.security.tokens.user.User_CanRead_Token;
import ua.com.fielden.platform.security.tokens.user.User_CanSave_Token;
import ua.com.fielden.platform.security.tokens.web_api.GraphiQL_CanExecute_Token;

/**
 * Transforms a tree of security tokens to another that incorporates TG platfrom specific tokens under the right top-level application modules.
 *
 * @author Generated
 */
public class SecurityTokenNodeTransformation implements ISecurityTokenNodeTransformation {

    private static final Map<Class<? extends ISecurityToken>, Set<Class<? extends ISecurityToken>>> tokensToRestructure = mapOf(
            t2(AppAdminModuleToken.class,
               setOf(Attachment_CanSave_Token.class, Attachment_CanRead_Token.class, Attachment_CanReadModel_Token.class, Attachment_CanDelete_Token.class, AttachmentMaster_CanOpen_Token.class, AttachmentDownload_CanExecute_Token.class,
                     GraphiQL_CanExecute_Token.class,
                     KeyNumber_CanRead_Token.class, KeyNumber_CanReadModel_Token.class,
                     DomainExplorer_CanRead_Token.class, DomainExplorer_CanReadModel_Token.class,
                     DashboardRefreshFrequencyUnit_CanRead_Token.class, DashboardRefreshFrequencyUnit_CanReadModel_Token.class,
                     DashboardRefreshFrequency_CanSave_Token.class, DashboardRefreshFrequency_CanRead_Token.class, DashboardRefreshFrequency_CanReadModel_Token.class, DashboardRefreshFrequency_CanDelete_Token.class, DashboardRefreshFrequencyMaster_CanOpen_Token.class)),
            t2(UsersAndPersonnelModuleToken.class,
               setOf(User_CanSave_Token.class, User_CanRead_Token.class, User_CanReadModel_Token.class, User_CanDelete_Token.class, UserMaster_CanOpen_Token.class,
                     UserRole_CanSave_Token.class, UserRole_CanRead_Token.class, UserRole_CanReadModel_Token.class, UserRole_CanDelete_Token.class, UserRoleMaster_CanOpen_Token.class,
                     UserAndRoleAssociation_CanRead_Token.class, UserAndRoleAssociation_CanReadModel_Token.class,
                     UserRolesUpdater_CanExecute_Token.class, UserRoleTokensUpdater_CanExecute_Token.class)));

    @Override
    public SortedSet<SecurityTokenNode> transform(final SortedSet<SecurityTokenNode> topLevelSecurityTokenNodes) {
        return topLevelSecurityTokenNodes.stream()
                .filter(node -> !tokensToRestructure.values().stream().anyMatch(set -> set.contains(node.getToken())))
                .map(node -> {
                    final SecurityTokenNode newNode = new SecurityTokenNode(node.getToken());
                    concat(tokensToRestructure.getOrDefault(node.getToken(), Collections.emptySet()).stream(),
                           node.getSubTokenNodes().stream().map(SecurityTokenNode::getToken))
                    .forEach(token -> new SecurityTokenNode(token, newNode));
                    return newNode;
                })
                .collect(toCollection(TreeSet::new));
    }

}
