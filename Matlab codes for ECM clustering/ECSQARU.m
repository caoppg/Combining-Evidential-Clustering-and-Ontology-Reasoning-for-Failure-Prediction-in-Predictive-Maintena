%generate random cost, uniformly distributed from [0,100] 
Cost = 0 + (100-0).*rand(size(Time,1),1);
Total=[Time,Cost,Support]
%credal partition for Time and Cost. A 3-credal partition is imposed so that 2^3=8 focal elements have been considered in the optimization process
Costbba=ECM(Cost, 3)
Timebba=ECM(Time, 3)
%discounting using chronicle support, mdiscount(x)=(1-k)*m(x)
for i= 1:size(Support,1)
    X(:,1)=Costbba(i,:)'
    Y(:,1)=Timebba(i,:)'
    A(i,:)=discounting(X(:,1),Support(i,1))
    B(i,:)=discounting(Y(:,1),Support(i,1))
end
%conjunctive rule of combination, two bbas
for j=1:size(A,1)
q1=mtoq(A(j,:));
q2=mtoq(B(j,:));
qConj=q1.*q2;
mConj=qtom(qConj);
Betp=mtobetp(mConj); % sum to 1
Result(j,:)=Betp;
end
%the  final  level  of criticality, apply argmax. The first column of
%matrix "Decision" is the max pignistic probability , and the second column is the
%cluster to which the max pignistic probability belongs.
for k=1:size(A,1)
    [argvalue, argmax] = max(Result(k,:))
    %max pignistic probability 
    Decision(k,1)=argvalue
    % which cluster the max  pignistic probability belongs to
    Decision(k,2)=argmax
end
%show combined data for Time+Cost


