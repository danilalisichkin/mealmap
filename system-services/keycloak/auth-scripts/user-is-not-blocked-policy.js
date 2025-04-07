var context = $evaluation.getContext();
var user = context.getIdentity().getSubject();
var attributes = user.getAttributes();

var isBlocked = attributes.get('isBlocked') != null && attributes.get('isBlocked').contains('true');
var isTemporaryBlocked = attributes.get('isTemporaryBlocked') != null && attributes.get('isTemporaryBlocked').contains('true');

if (!isBlocked && !isTemporaryBlocked) {
    $evaluation.grant();
} else {
    $evaluation.deny();
}
